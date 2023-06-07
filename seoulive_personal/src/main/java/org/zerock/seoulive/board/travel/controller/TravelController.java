package org.zerock.seoulive.board.travel.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.seoulive.board.travel.domain.Criteria;
import org.zerock.seoulive.board.travel.domain.DTO;
import org.zerock.seoulive.board.travel.domain.PageDTO;
import org.zerock.seoulive.board.travel.domain.VO;
import org.zerock.seoulive.board.travel.exception.ControllerException;
import org.zerock.seoulive.board.travel.service.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Log4j2
@NoArgsConstructor
@Controller
@RequestMapping("/board/travel/*")
public class TravelController {

    @Setter(onMethod_ = {@Autowired})
    private Service service;

    // 게시판 목록조회
    @GetMapping("/list")
    public void list(Criteria cri, Model model) throws ControllerException {
        log.trace("list({}, {}) invoked.", cri, model);

        try {
            List<VO> list = this.service.getList(cri);

            model.addAttribute("__LIST__",list);

            PageDTO pageDTO = new PageDTO(cri, this.service.getTotal(cri));
            model.addAttribute("pageMaker", pageDTO);
            model.addAttribute("list", service.getList(cri));
        } catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
    }

    // 날짜 데이터 받아오기
    @RequestMapping(value = "/board/travel/listByDate", method = RequestMethod.GET)
    @ResponseBody
    public List<String> processClickedDate(@RequestParam("selectedDate")
                                           String selectedDate, Model model)
            throws ControllerException {
        try {
            // selectedDate에서 년, 월, 일을 추출
            String[] dateParts = selectedDate.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            List<String> searchList = getSearchList(); // 실제 검색 대상 리스트를 얻는 메서드 호출

            List<String> selectDate = new ArrayList<>();
            for (String item : searchList) {
                // 항목에서 년도, 월, 일을 추출하여 검색 조건과 비교
                String[] itemParts = item.split("-");
                int itemYear = Integer.parseInt(itemParts[0]);
                int itemMonth = Integer.parseInt(itemParts[1]);
                int itemDay = Integer.parseInt(itemParts[2]);

                if (itemYear == year && itemMonth == month && itemDay == day) {
                    selectDate.add(item);
                }
            }

            model.addAttribute("selectDate", selectedDate); // EL 변수를 모델에 추가

            return selectDate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ControllerException(e);
        }
    }

    // 실제 검색 대상 리스트를 반환하는 메서드
    private List<String> getSearchList() {
        List<String> searchList = new ArrayList<>();
        // 검색 대상 리스트를 얻는 로직을 구현
        // 예시로 임의의 데이터를 추가
        searchList.add("2023-05-23");
        searchList.add("2023-05-24");
        searchList.add("2023-05-25");
        return searchList;
    }

    @PostMapping(value = "/write")
    String write(DTO dto, RedirectAttributes rttrs) throws ControllerException {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            Objects.requireNonNull(dto);

            if(this.service.register(dto)) {
                rttrs.addFlashAttribute("result", true);
                rttrs.addFlashAttribute("seq", dto.getSeq());
            } // end if
            return "redirect:/board/travel/list";
        }
        catch(Exception e) {
            throw new ControllerException(e);
        } // try-catch
    }

    @GetMapping("/write")
    void write() {	// 단순 수정 화면 요청

    } //

    @GetMapping(path = "/view", params = "seq")
    void view(@RequestParam("seq") Integer seq, Model model) throws ControllerException {

        try {
            VO vo = this.service.get(seq);
            model.addAttribute("__BOARD__", vo);
            this.service.total(seq);
        }
        catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // view

    @PostMapping("/modify")
    String modify(DTO dto, RedirectAttributes rttrs) throws ControllerException {
        log.info("modify({}, {}) invoked.", dto, rttrs);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            Objects.requireNonNull(dto);

            Boolean result = this.service.modify(dto);
                rttrs.addAttribute("result", result);
                rttrs.addAttribute("seq", dto.getSeq());

                return "redirect:/board/travel/view";
        }
        catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // modify

    @GetMapping("/modify")
    void modify(Integer seq, Model model) throws ControllerException {	// 단순 수정 화면 요청

        try {
           VO vo = this.service.get(seq);
           model.addAttribute("__BOARD__", vo);

        }
        catch (Exception e) {
            throw new ControllerException(e);
        }
    } //

    @PostMapping("/remove")
    String remove(Integer seq, RedirectAttributes rttrs) throws ControllerException {

        try {
//            rttrs.addAttribute("currPage", currPage);

            Boolean result = this.service.remove(seq);
                rttrs.addAttribute("result", result);
                rttrs.addAttribute("seq", seq);

            return "redirect:/board/travel/list";
        }
        catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
    } // remove
}

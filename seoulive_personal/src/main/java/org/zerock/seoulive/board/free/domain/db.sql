SELECT *
FROM
    TBL_FREE
ORDER BY
    SEQ desc;

SELECT *
FROM TBL_FREE
WHERE seq > 0;

UPDATE TBL_FREE set total_count=total_count+1 where seq=1;



UPDATE TBL_FREE set title='공지사항',writer='이동영',content='안녕하세요 저는 이동영 이라고 합니다.',category='category2',modify_date=SYSDATE where seq=4;

-- CREATE TABLE tbl_free (
--   seq	      NUMBER(8)		 NOT NULL,
--   writer	  VARCHAR2(50)	 NOT NULL,
--   category	  VARCHAR2(20)	 NOT NULL,
--   title	       VARCHAR2(200)		 NOT NULL,
--   content	  VARCHAR2(2000) NOT NULL,
--   write_date	  SYSDATE(4) SYSDATE,
--   modify_date	  DATE NULL,
--   total_count	  NUMBER(8)		DEFAULT 0
-- );

CREATE TABLE tbl_free (
                          seq	        NUMBER(8)  PRIMARY KEY,
                          writer	    VARCHAR2(50)	 NOT NULL,
                          category	  VARCHAR2(20)	 NOT NULL,
                          title	      VARCHAR2(200)	 NOT NULL,
                          content	    VARCHAR2(2000) NOT NULL,
                          write_date	DATE DEFAULT SYSDATE,
                          modify_date	DATE NULL,
                          total_count	NUMBER(8)		 DEFAULT 0

);
CREATE SEQUENCE seq_tbl_free START WITH 1 INCREMENT BY 1;

ALTER TABLE tbl_free ADD CONSTRAINT "PK_TBL_FREE" PRIMARY KEY (
                                                               seq
    );




drop table TBL_FREE;


INSERT INTO tbl_free (seq, writer, category, title, content, write_date, total_count)
VALUES (seq.nextval, '관리자', '카테고리', '공지사항', '공지사항 입니다.', sysdate);



INSERT INTO tbl_free (writer, category, title, content, total_count)
VALUES ('이동영', '카테고리', '공지사항', '공지사항 입니다.',null, 100);

DROP SEQUENCE seq_tbl_free;



CREATE SEQUENCE seq_tbl_free START WITH 1 INCREMENT BY 1 MAXVALUE 10000 CYCLE NOCACHE;

commit;


INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (1,'이동영','테스트공지사항',sysdate,null,'freeboard',3);


INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (1,'이동영','테스트공지사항',sysdate,null,'freeboard',3);


INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (2,'이동영','테스트공지사항',sysdate,null,'freeboard',3);
INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (3,'이동영','테스트공지사항',sysdate,null,'freeboard',3);
INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (4,'이동영','테스트공지사항',sysdate,null,'freeboard',3);
INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (5,'이동영','테스트공지사항',sysdate,null,'freeboard',3);


INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (6,'관리자','테스트공지사항',sysdate,null,'review',4);
INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (7,'관리자','테스트공지사항',sysdate,null,'review',4);
INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (8,'관리자','테스트공지사항',sysdate,null,'review',4);
INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (9,'관리자','테스트공지사항',sysdate,null,'review',4);
INSERT INTO tbl_comment (seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (10,'관리자','테스트공지사항',sysdate,null,'review',4);

SELECT *
FROM
    TBL_COMMENT
where seq=8 AND BOARD_NAME = 'freeboard';


WHERE
    BOARD_NAME='freeboard' AND POST_SEQ=20;

SELECT
    COUNT(SEQ) AS comment_count
FROM
    TBL_COMMENT

WHERE
        BOARD_NAME='freeboard' AND POST_SEQ=3;

SELECT *
FROM
    TBL_COMMENT
WHERE
        BOARD_NAME='freeboard' AND POST_SEQ=${post_seq};

CREATE SEQUENCE SEQ_TBL_COMMENT START WITH 1 INCREMENT BY 1 MAXVALUE 10000 CYCLE NOCACHE;


INSERT INTO tbl_comment(seq,writer,content,write_date,modify_date,board_name,post_seq)
VALUES (SEQ_TBL_COMMENT.nextval,'관리자','안녕하세요',sysdate,null,'freeboard',5);

delete from tbl_comment where seq=5;

commit;

update tbl_comment set content='수정테스트', modify_date=sysdate
where seq=8 AND BOARD_NAME = 'freeboard' AND post_seq=20;

SELECT *
FROM
    TBL_COMMENT
where seq=8 AND post_seq=20 AND board_name = 'freeboard';

update tbl_comment set content='수정 테스트 입니다..', modify_date=sysdate
where seq=8 AND board_name = 'freeboard' AND post_seq = 20;

rollback;


INSERT INTO tbl_free (seq, writer, category, title, content, write_date, modify_date, total_count)
VALUES (27, '이동영짱27', '카테고리', '공지사항', '공지사항 입니다.', TO_DATE('2023-05-11', 'YYYY-MM-DD'), null, 100);


DELETE FROM tbl_free;

commit;

SELECT *
FROM
    TBL_COMMENT;

delete from tbl_comment where post_seq=19;
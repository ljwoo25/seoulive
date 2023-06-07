SELECT *
FROM
    TBL_REVIEW;

SELECT *
FROM
    TBL_COMMENT;

SELECT * FROM TBL_REVIEW ORDER BY SEQ desc;

CREATE SEQUENCE seq_tbl_review START WITH 1 INCREMENT BY 1 MAXVALUE 10000 CYCLE NOCACHE;

INSERT INTO TBL_REVIEW (seq,title,content,writer,place)
VALUES (seq_tbl_review.nextval,'공지사항2','서울라이브 공지사항입니다.','이주호','서울시 강남구')

DELETE FROM TBL_REVIEW WHERE seq = 99
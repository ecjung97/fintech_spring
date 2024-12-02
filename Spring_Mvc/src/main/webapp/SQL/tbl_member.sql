-- tbl_member8테이블 생성
create table tbl_member8(
  userid varchar2(50) primary key --회원 아이디
  ,userpw varchar2(100) not null --비번
  ,username varchar2(20) not null --회원이름
  ,email varchar2(100) --이메일
  ,regdate date default sysdate --가입날짜  
);

select * from tbl_member8;

-- tbl_board 테이블을 생성
create table tbl_board(
  bno number(38) primary key --게시판 번호
  , writer varchar2(50) not null --글작성자
  , title varchar2(200) not null --글제목
  , content varchar2(4000) not null --글내용
  , viewcnt int default 0 --조회수, default 0제약조건을 주면 해당 컬럼에 굳이 레코드를 저장하지 않아도
  --기본값 0이 저장됨.
  , regdate date -- 글 등록날짜
);

select * from tbl_board order by bno desc;

-- bno_seq 시퀀스 생성
create sequence bno_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; --임시 메모리 사용하지 않음

-- bno_Seq 시퀀스 다음번호값 확인
select bno_seq.nextval as "시퀀스 번호" from dual;

-- tbl_reply 댓글 테이블 작성
create table  tbl_reply(
    rno number(38) primary key, -- 댓글 번호
    bno number(38) default 0, -- 외래키(foreign key) 제약조건으로 추가설정. tbl_board 게시판 테이블의 bno column 번호값만 저장됨.
    replyer varchar2(100) not null, -- 댓글 작성자
    replytext varchar2(4000) not null, -- 댓글 내용
    regdate date, -- 등록 날짜
    updatedate date -- 수정 날짜
);
select * from tbl_reply order by rno desc;

-- 외래키 추가설정
alter table tbl_reply add constraint tbl_reply_bno_fk
foreign key (bno) references tbl_board(bno);

-- 댓글 시퀀스 생성
create sequence rno_seq
start with 1
increment by 1
nocache ;

-- rno_seq 다음 시퀀스 번호값 확인
select rno_seq.nextval as "다음 시퀀스 번호값" from dual;



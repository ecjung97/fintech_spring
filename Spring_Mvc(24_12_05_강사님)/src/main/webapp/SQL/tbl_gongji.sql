--tbl_gongji7 공지사항 테이블 생성
create table tbl_gongji7(
  gno number(38) primary key --  공지번호
  , gname varchar2(50) not null -- 공지 작성자
  , gtitle varchar2(200) not null -- 공지 제목
  , gcont varchar2(4000) not null -- 공지 내용
  , ghit number(38) default 0 --조회수
  , gdate date  default sysdate --등록날짜
);

select * from tbl_gongji7 order by gno desc;

--gno_seq7 시퀀스 생성
create sequence gno_seq7
start with 1
increment by 1
nocache;

--gno_seq7 시퀀스 번호 확인
select gno_seq7.nextval as "시퀀스 번호값" from dual;
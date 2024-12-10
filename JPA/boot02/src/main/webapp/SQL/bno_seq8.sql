-- bno_seq8 시퀀스 메모리를 nocache로 수정
alter sequence bno_seq8
nocache; -- nocache로 수정

alter sequence fno_seq
nocache;

select * from tbl_boards3 order by bno desc;

select * from tbl_members6 order by uid2 asc;

select * from tbl_profile3 order by fno;

--delete from TBL_BOARDS3 where bno=6;
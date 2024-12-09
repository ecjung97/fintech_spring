-- bno_seq8 시퀀스 메모리를 nocache로 수정
alter sequence bno_seq8
nocache; -- nocache로 수정

select * from tbl_boards3 order by bno desc;

--delete from TBL_BOARDS3 where bno=6;
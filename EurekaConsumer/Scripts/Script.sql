	
TRUNCATE table DBNCHKP ;
TRUNCATE table DBNDAIL ;
TRUNCATE table DBNFURI ;
TRUNCATE table DBNIDMS ;
TRUNCATE table DBNMAIN ;
TRUNCATE table DBNTRAN ;

TRUNCATE table DBNULOG_1 ;
TRUNCATE table DBNULOG_2 ;

select
	*
from
	DBNCHKP
WHERE
	(CHKP_KEY = 'CHK01')
order by
	CHKP_KEY COLLATE SQL_EBCDIC037_CP1_CS_AS offset 0 rows fetch next 3000 rows only;

select * from DBNMAIN d where d.MA_SYABAN ='0A0602';

select MA_KN_YY  from DBNMAIN d where d.MA_SYABAN ='0A0600';

SELECT * from dbo.REPORTCONFIG r ;


SELECT  * from DBNTRAN d ;


SELECT * from dbuser d ;


select
	*
from
	DBNULOG_1 t
where
	t.SEQ_NO >= (
	select
		SEQ_NO
	from
		DBNULOG_1 with (updlock,
		nowait)
	WHERE
		(ULOG_KEY = '1') )
order by
	ULOG_KEY offset 0 rows fetch next 3000 rows only;





select
	*
from
	DBNULOG_2 with (updlock)
WHERE
	ULOG_KEY >= '1'
order by
	ULOG_KEY offset 0 rows fetch next 3000 rows only
	
	
	
select
	*
from
	DBNULOG_2 t
where
	t.SEQ_NO >= (
	select
		SEQ_NO
	from
		DBNULOG_2 with (updlock)
	WHERE
		(ULOG_KEY = '1') )
order by
	ULOG_KEY offset 0 rows fetch next 3000 rows only
	
	
	
begin tran 
select * from dbo.DBNCHKP d with (rowlock,updlock) --holdlock  行锁
where d.CHKP_KEY  ='CHK01' 
waitfor delay '00:00:59' --等待30秒 
commit tran;

begin tran 
select * from dbo.DBNMAIN d with (rowlock,updlock) --holdlock  行锁
where d.MA_SYABAN  ='0A0600' 
waitfor delay '00:00:11' --等待30秒 
commit tran;

begin tran 
select * from dbo.DBNDAIL d with (rowlock,updlock) --holdlock  行锁
waitfor delay '00:00:12' --等待30秒 
commit tran;

begin tran 
select * from dbo.DBNTRAN d with (updlock,nowait) --holdlock  行锁
waitfor delay '00:00:12' --等待30秒 
commit tran;

SELECT * from DBNMAIN d with(updlock,nowait);

select * from dbnchkp d with(updlock,nowait);

select * from DBNCHKP t where t.SEQ_NO >= ( select SEQ_NO from DBNCHKP with (updlock,nowait) WHERE (CHKP_KEY = 'CHK01') ) order by CHKP_KEY COLLATE SQL_EBCDIC037_CP1_CS_AS offset 0 rows fetch next 3000 rows only

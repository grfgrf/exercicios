
--EACH DEP - TOP 3 SAL w/o FETCH

with emp_rows as (
                  select t.departmentid, 
                         t.salary,
                         row_number () over (partition by t.departmentid order by t.salary desc) rn
                  from   Employee t
                  group by t.salary,t.departmentid
                 )
                 
select d.name as "Department",
       e.name as "Employee",
       e.salary as "Salary" 
from employee e 
join department d 
    on e.departmentid=d.id 
where
  (e.departmentid, e.salary) in (select  er.departmentid,er.salary from emp_rows er 
                                  where  er.rn <= 3)


-----


SELECT t.Request_at as "Day", 
     ROUND((Count((CASE WHEN t.status<>'completed' THEN t.id END)) / count(*)),2) as "Cancellation Rate"
FROM TRIPS t
WHERE t.Request_at between ('2013-10-01') and ('2013-10-03')
      AND t.client_id not in(Select Users_Id FROM Users u WHERE  u.banned='Yes'AND  u.role='client') 
GROUP BY t.Request_at
ORDER BY t.Request_at

----



SELECT s.id as "id",
       to_char(s.visit_date,'YYYY-MM-DD') as "visit_date",
       s.people as "people" FROM stadium s
WHERE s.id IN (
             SELECT DISTINCT idsfiltrados --RETURN 1 COLUMN WITH ALL VALIDATED IDs, 
                   FROM (
                         SELECT idLeft,idMid,idRight--ALL IDS WITH >=3 CONSECUTIVES ROWS
                         FROM(
                               SELECT LAG(id,1,-1)OVER (order by ID asc) as idLeft,--ID-1,ID,ID+1
                               id as idMid,                                        --COM POP>=100
                               LEAD(id,1,-1)OVER (order by ID asc) as idRight
                               FROM STADIUM
                               WHERE People>=100
                             ) allIDs  
                         WHERE allIDs.idLeft+1=allIDs.idMid and allIDs.idRight-1=allIDs.idMid
                        )
            UNPIVOT(idsfiltrados FOR cols IN (idLeft, idMid, idRight))
            )
ORDER BY s.visit_date ASC;
        
-----

CREATE FUNCTION getNthHighestSalary(N IN NUMBER) RETURN NUMBER IS
result NUMBER;
BEGIN
  
    /* Write your PL/SQL query statement below */
     
       --SELECT (CASE WHEN b.maisqueum <2 THEN b.salary
               --ELSE null END)
        SELECT nvl(b.Salary, NULL)
       INTO result 
       FROM (Select a.salary,rownum as rnum 
                FROM(SELECT SALARY
                        FROM EMPLOYEE 
                        group by salary
                        ORDER BY SALARY DESC
                    )a
            )b
       WHERE b.rnum = N; 
              
     
RETURN result;
END;
            

----

--SCORE DESC> SE IGUAL: MESMO RANK ELSE RANK+1

SELECT score as "score", 
       DENSE_RANK() OVER (ORDER BY Score desc) as "Rank"
FROM Scores;

----

Select c.ConsecutiveNums from (
                select (CASE WHEN lead(num,0)over(order by rownum asc) = NUM
                        AND lead(num,1)over(order by rownum asc) = NUM
                        AND lead(num,2)over(order by rownum asc) = NUM
                        THEN Num
                        END) as ConsecutiveNums
                from logs
              ) c
where c.ConsecutiveNums is NOT NULL
group by c.ConsecutiveNums

-----

SELECT d.name as "Department", e.name as "Employee", e.salary as "Salary"
FROM employee e
    JOIN department d
    ON e.departmentid=d.id
WHERE (e.departmentID, e.salary) in (SELECT departmentID, MAX(salary)
                                FROM EMPLOYEE
                                GROUP BY departmentID);

-----


SELECT      (CASE 
             WHEN MOD(s1.ID,2)= 1 AND s1.id=(select count(id) from seat) THEN s1.id
             WHEN MOD(s1.ID,2) = 1 THEN s1.id+1
             WHEN MOD(s1.ID,2) = 0 THEN s1.id-1 
              END
             ) as "id", s1.student
FROM seat s1
order by 1;

-----

SELECT p.FirstName,p.LastName,a.City,a.State
FROM PERSON P 
    LEFT JOIN ADDRESS A
    ON p.personId=a.personId


------

SELECT max(e.salary) as "SecondHighestSalary"
from employee e ,
    (select MAX(salary)as maxsal from employee
    ) max
where e.salary <> max.maxsal

-----

WITH manager_salary AS(
SELECT DISTINCT E.SALARY, MS.MANAGERID 
    FROM employee e,
         (SELECT MANAGERID FROM EMPLOYEE WHERE MANAGERID is not null)ms
    WHERE e.id=ms.managerID
)
            
select name as "Employee" 
from employee e
JOIN  manager_salary ms 
    ON e.managerid=ms.managerid 
       and e.salary>ms.salary

-----

SELECT email AS "Email" FROM person
GROUP BY email
HAVING count(email)>1

-----

SELECT C.NAME as "Customers"
FROM CUSTOMERS C
WHERE C.ID NOT IN (select O.CUSTOMERID FROM ORDERS O)--C.ID = 0 orders

-----

SELECT w.id as "Id" 
FROM (
      SELECT ID,--All datas needed: ID, currentDATE, yesterdayDATE, currentDayTemp, yesterdayTemp.
             RECORDDATE today,
             LAG(RecordDate,1) OVER (order by RecordDate asc) as yesterday,
             TEMPERATURE as temp_today,
             LAG(Temperature,1) OVER (order by recordDate asc) as temp_yesterday
      FROM WEATHER
     ) w
WHERE
    (w.today-w.yesterday=1)  -- Check if previous date row is yesterday.  
    AND 
    (w.temp_today>w.temp_yesterday) -- Check if current temp > yesterday temp


----

update salary 
   set sex =(case when (sex='m') then 'f' else'm' end)



              
                
        

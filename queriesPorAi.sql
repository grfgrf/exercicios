--1
SELECT model,speed,hd FROM pc WHERE price <500

--2
SELECT DISTINCT maker FROM product WHERE type = 'Printer'

--3
SELECT model,ram,screen FROM laptop WHERE price >1000

--4
SELECT * FROM printer WHERE color='y'

--5
SELECT model,speed,hd 
  FROM pc 
 WHERE price <600 AND cd IN ('12x','24x')

--6 
SELECT DISTINCT p.maker,l.speed 
  FROM product p, laptop l
 WHERE p.model=l.model 
       AND l.hd >=10

--7
SELECT all_p.model, all_p.price 
  FROM (
        SELECT model, price FROM pc
         UNION
        SELECT model, price FROM laptop
         UNION
        SELECT model, price FROM printer
        ) all_p 
  JOIN product p 
    ON p.model=all_p.model 
       AND p.maker='B'

--8
SELECT DISTINCT maker FROM product WHERE type='PC'
EXCEPT --MINUS
SELECT DISTINCT maker FROM product WHERE type='Laptop'

--9
SELECT DISTINCT maker 
  FROM product 
 WHERE model IN (SELECT model FROM pc WHERE speed >= 450)

--10
SELECT model,price 
  FROM printer 
 WHERE price = (SELECT MAX(price) FROM printer)

--11
SELECT AVG(speed) FROM pc

--12
SELECT AVG(speed) FROM laptop WHERE price > 1000

--13
SELECT AVG(speed) 
  FROM pc p 
       JOIN product p2 
         ON p.model = p2.model 
            AND p2.maker = 'A'

--14
SELECT s.class, s.name, c.country
  FROM ships s 
        JOIN classes c 
          ON c.class = s.class
             AND  c.numguns >= 10

--15
SELECT hd FROM pc GROUP BY hd HAVING COUNT(hd)>1

--16
SELECT DISTINCT p.model,
                p2.model,
                p2.speed,
                p2.ram 
  FROM pc p 
       JOIN pc p2 
         ON p.ram = p2.ram 
            AND p.speed = p2.speed
            AND p.model > p2.model

--17
SELECT DISTINCT p.type, l.model,l.speed 
  FROM laptop l 
         JOIN product p 
              ON l.model=p.model
  WHERE l.speed < ALL (SELECT speed FROM PC)

--18
SELECT DISTINCT p.maker,
                pr.price
           FROM product p 
           JOIN printer pr 
             ON p.model=pr.model 
                AND pr.color='y'
                AND pr.price = (SELECT MIN(price)FROM printerWHERE color='y')

--19
  SELECT p.maker, AVG(l.screen) 
    FROM product p 
    JOIN laptop l 
      ON p.model = l.model
GROUP BY p.maker

--20
    SELECT maker, COUNT(model)
      FROM product p
     WHERE type='PC'
  GROUP BY maker
    HAVING COUNT(DISTINCT model) > 2
    
--21
    SELECT p.maker, MAX(p2.price)
      FROM product p
      JOIN pc p2 
        ON p.model=p2.model
           AND p.type='Pc'
  GROUP BY p.maker

--22
    SELECT pc.speed, AVG(pc.price)
      FROM pc
     WHERE speed>600
  GROUP BY pc.speed

--23
    SELECT maker 
      FROM product 
     WHERE type='PC' 
           AND model IN (SELECT model FROM pc WHERE speed>=750)
 INTERSECT
    SELECT maker 
      FROM product 
     WHERE type='Laptop' 
           AND model IN (SELECT model FROM laptop WHERE speed>=750)

--24
WITH tudo AS 
   (
    SELECT model, price FROM PC
     UNION
    SELECT model, price FROM Laptop
     UNION
    SELECT model, price FROM Printer
    )
SELECT model FROM tudo WHERE price >= (SELECT MAX(Price) FROM tudo)

--25
SELECT a.maker 
  FROM (
           SELECT maker from product WHERE type='Pc'
        INTERSECT
           SELECT maker FROM product WHERE type='Printer'
           )a
 WHERE a.maker IN (
                   SELECT maker 
                     FROM product
                    WHERE model IN (
                                    SELECT model FROM pc 
                                     WHERE ram = (SELECT MIN(ram)FROM pc) 
                                           AND speed = (
                                                        SELECT MAX(speed)
                                                          FROM pc 
                                                         WHERE ram=(SELECT MIN(ram)FROM pc)
                                                        )
                                    )
                  )

--26
WITH pcpr AS (
SELECT maker  FROM product WHERE type='PC'
INTERSECT
SELECT maker FROM product WHERE type='Printer')

SELECT p.maker,AVG(pc.hd)
  FROM product p JOIN pc ON pc.model=p.model AND p.maker IN (SELECT maker FROM pcpr)
GROUP BY p.maker

--27
WITH pcpr AS 
   (
       SELECT maker FROM product WHERE type='PC'
    INTERSECT
       SELECT maker FROM product WHERE type='Printer'
   )

  SELECT p.maker,
         AVG(pc.hd)
    FROM product p 
    JOIN pc 
      ON pc.model=p.model 
            AND p.maker IN (SELECT maker FROM pcpr)
GROUP BY p.maker

--28
SELECT COUNT(*) 
FROM (
      SELECT   maker 
      FROM     product 
      GROUP BY maker
        HAVING COUNT(DISTINCT model) = 1
      ) t

--29
   (SELECT i.point,
           i.date,
           i.inc,
           o.out 
      FROM income_o i 
 LEFT JOIN outcome_o o 
        ON i.point = o.point 
           AND i.date = o.date)
     UNION
   (SELECT o.point,
           o.date,
           i.inc,
           o.out 
      FROM outcome_o o 
 LEFT JOIN income_o i 
        ON i.point=o.point 
           AND i.date=o.date)

--30
    SELECT point,
           date,
           SUM(out),
           SUM(inc)
      FROM(
           (SELECT point,date,null out,SUM(inc) inc FROM income GROUP BY date,point) 
            UNION
           (SELECT point,date,SUM(out) out,null inc FROM outcome GROUP by date,point)
          ) t
  GROUP BY date,point

--31
SELECT DISTINCT class, country FROM classes WHERE bore >= 16.0

--32
    SELECT country,
           CAST(AVG(POWER(bore,3)/2) AS DECIMAL(7,2)) 
      FROM (
            (SELECT s.name, c.country, c.bore 
               FROM classes c JOIN ships s ON s.class=c.class)
              UNION
            (SELECT o.ship, c.country, c.bore 
               FROM outcomes o JOIN classes c ON o.ship=c.class)
           ) t
GROUP BY country

--33
SELECT ship FROM outcomes WHERE battle='North Atlantic' AND result='sunk'

--34
    SELECT s.name 
      FROM classes c 
RIGHT JOIN ships s 
        ON s.class=c.class
     WHERE displacement>35000 
           AND launched>=1922 
           AND type='bb'

--35
    SELECT model, type 
      FROM product 
     WHERE model NOT LIKE '%[^A-Z]%'
           OR model NOT LIKE '%[^0-9]%'

--36
    SELECT name FROM (
                      (SELECT o.ship name,
                              c.class 
                         FROM outcomes o 
                         JOIN classes c ON o.ship=c.class)
                        UNION
                      (SELECT name, class FROM ships)
                     ) t
               WHERE name=class


--37
    SELECT class FROM (
                       (SELECT o.ship,
                               c.class 
                          FROM classes c 
                          JOIN outcomes o ON o.ship=c.class)
                         UNION
                       (SELECT name, class FROM ships)
                      )t
             GROUP BY class
               HAVING COUNT(ship)=1

--38
(SELECT country FROM classes where type='bc')
INTERSECT
(SELECT country FROM classes where type='bb')

--39
SELECT DISTINCT kk.ship 
           FROM (
                 SELECT ship, date 
                   FROM outcomes oo, battles bb 
                  WHERE oo.battle=bb.name
                 ) tt 
           JOIN
                (
                 SELECT ship, date AS damagedate 
                   FROM outcomes oo, battles bb 
                  WHERE result ='damaged' 
                    AND oo.battle=bb.name
                ) kk 
              ON tt.ship=kk.ship 
                 AND tt.date>kk.damagedate

--40
 SELECT DISTINCT maker, type
            FROM product 
           WHERE maker IN (
                           SELECT maker 
                             FROM product
                         GROUP BY maker
                           HAVING COUNT(DISTINCT type)=1 
                                  AND COUNT( DISTINCT model)>1
                           )

--41
    SELECT maker,
           iif(COUNT(*)=COUNT(price),MAX(price),NULL) m_price 
      FROM product p
      JOIN(
           (SELECT model, price FROM pc)
            UNION ALL
           (SELECT model, price FROM laptop)
            UNION ALL
           (SELECT model, price FROM printer)
          ) plp
        ON p.model = plp.model
  GROUP BY maker

--42
SELECT ship,battle FROM outcomes WHERE result='sunk'

--43
    SELECT name 
      FROM battles b 
     WHERE NOT EXISTS( 
                      SELECT launched 
                        FROM ships s 
                       WHERE s.launched=DATEPART(year,b.date)
                      )

--44
(SELECT ship FROM outcomes WHERE ship LIKE 'R%')
UNION
(SELECT name FROM ships WHERE name LIKE 'R%')

--45
    SELECT ship
      FROM( 
           (SELECT ship FROM outcomes)
            UNION
           (SELECT name FROM ships)
          ) t
     WHERE charindex(' ',ship)<>0 
           AND charindex(' ',ship,charindex(' ',ship)+1)<>0

--46
    SELECT ship, displacement, numguns 
      FROM (
            SELECT o.ship , 
                   COALESCE(s.class, o.ship) class 
              FROM outcomes o
         LEFT JOIN ships s 
                ON s.name = o.ship
             WHERE o.battle= 'Guadalcanal'
           ) t 
 LEFT JOIN classes c 
        ON c.class=t.class

--47 
WITH total AS(
SELECT country,count(name) conta FROM (
SELECT c.country, s.name FROM classes c JOIN ships s ON s.class=c.class
union
select c.country, o.ship FROM classes c JOIN outcomes o ON o.ship=c.class
) t 
GROUP BY country),

afundados AS (SELECT country,count(ship) conta FROM (
SELECT c.country, s.name FROM classes c JOIN ships s ON s.class=c.class
union
select c.country, o.ship FROM classes c JOIN outcomes o ON o.ship=c.class
) t JOIN outcomes o ON t.name=o.ship
WHERE o.result='sunk'
group by country)

select t.country from total t JOIN afundados a ON a.country=t.country
WHERE a.conta=t.conta

--48
SELECT DISTINCT t.class 
           FROM (
                 SELECT c.class,
                        COALESCE(s.name,c.class)name 
                   FROM classes c 
              LEFT JOIN ships s ON s.class=c.class
                ) t
           JOIN outcomes o ON name=o.ship
          WHERE o.result='sunk'

--49
(SELECT s.name FROM ships s LEFT JOIN classes c ON c.class=s.class WHERE c.bore=16)
UNION
(SELECT c.class FROM outcomes o JOIN classes c ON c.class=o.ship WHERE c.bore=16)

--50
SELECT DISTINCT o.battle 
           FROM ships s 
           JOIN outcomes o ON o.ship=s.name
          WHERE s.class='Kongo'

--51
WITH allnames AS(
SELECT s.name,c.displacement,c.numguns FROM ships s JOIN classes c ON c.class=s.class
UNION
SELECT o.ship,c.displacement,c.numguns FROM outcomes o JOIN classes c ON o.ship=c.class
),
maxguns AS(SELECT MAX(numguns) guns,displacement FROM allnames GROUP BY displacement)


SELECT name FROM allnames a JOIN maxguns m ON (m.guns=a.numguns
and m.displacement=a.displacement)

--52
WITH allships AS(
SELECT s.name,c.class,c.displacement,c.numguns,c.country,c.type,c.bore FROM ships s JOIN classes c ON c.class=s.class
UNION
SELECT o.ship,c.class,c.displacement,c.numguns,c.country,c.type,c.bore FROM outcomes o JOIN classes c ON o.ship=c.class
)

SELECT name FROM allships
 WHERE  (type='bb' OR type  IS NULL) AND (numguns>=9 or numguns  IS NULL) 
        AND (bore<19 or bore  IS NULL) AND (displacement<=65000 or displacement  IS NULL) 
        AND (country='Japan' or country  IS NULL)


--53
SELECT ROUND(AVG(numguns),2) FROM classes WHERE type='bb'

--54
WITH allships AS(
SELECT s.name,c.class,c.displacement,c.numguns,c.country,c.type,c.bore FROM ships s JOIN classes c ON c.class=s.class
UNION
SELECT o.ship,c.class,c.displacement,c.numguns,c.country,c.type,c.bore FROM outcomes o JOIN classes c ON o.ship=c.class
)


SELECT ROUND(AVG(numguns),2) FROM allships WHERE type='bb'

--55
    SELECT c.class,min(launched) 
      FROM classes c 
 LEFT JOIN ships s 
           ON s.class=c.class 
  GROUP BY c.class

--56
WITH oeb AS
   (
     SELECT * FROM outcomes o JOIN battles b ON b.name=o.battle
   )

    SELECT class,
           COUNT((case when result='sunk' THEN 1 ELSE null END))
      FROM (
            (SELECT c.class, s.name, result 
               FROM classes c 
                    LEFT JOIN ships s 
                              ON s.class=c.class 
                    LEFT JOIN oeb o 
                              ON o.ship=s.name
             )
              UNION
            (SELECT c.class, o.ship, result 
               FROM classes c JOIN oeb o ON o.ship=c.class
            )
           ) t
  GROUP BY class


--58
WITH tmodels AS (
SELECT maker, COUNT(DISTINCT model) cmodel FROM product GROUP BY maker
),

tudo AS(
SELECT DISTINCT p.maker,
                t.type,
                p2.model 
           FROM product p 
     CROSS JOIN (SELECT type FROM product) t 
      LEFT JOIN product p2 ON p.maker=p2.maker 
                AND t.type=p2.type
)

    SELECT t.maker,
           t.type,
           ROUND((COUNT(t.model)/tm.cmodel)*100,2) 
      FROM tudo t 
 LEFT JOIN tmodels tm 
           ON t.maker=tm.maker
  GROUP BY t.maker,t.type,tm.cmodel
  ORDER BY 1

--59
-- coluna "out" e "date" error, usando novos nomes:
WITH monta_outcome AS (

select null as point,null as date1 ,null as inc from dual
union
select * from outcome_o where point is not null
),

outcome1_o as (
select * from monta_outcome where point is not null
),

monta_income AS (
select null as point,null as date1 ,null as inc from dual
union
select * from income_o
),
income1_o as (
select * from monta_income where point is not null
)
--fim troca nome coluna

   SELECT point,
          SUM(inc)-SUM(out) 
     FROM (
           SELECT point, inc, 0 out FROM income1_o
           UNION ALL
           SELECT point, 0 inc, inc FROM outcome1_o
          )
 GROUP BY point
--73
WITH oeb AS ( --column name "date" bug --trocando para bdate
             SELECT ship,name,bdate,result 
               FROM (
                     (SELECT null name,null bdate FROM battles) 
                       UNION                                    
                     (SELECT * from battles)
                    )t 
               JOIN outcomes o ON o.battle=t.name
             ),

tudotudo AS ( --view com todos dados das 4 tabelas 
             (SELECT c.class,s.name shipname,c.country,c.type,c.numguns,c.bore,
                     c.displacement,s.launched,o.name battlename,o.bdate,o.result
                FROM classes c JOIN ships s 
                                    ON s.class=c.class 
                          LEFT JOIN oeb o 
                                    ON o.ship=s.name)
               UNION
             (SELECT c.class,o.ship,c.country,c.type,c.numguns,c.bore,
                     c.displacement,s.launched,o.name,o.bdate,o.result 
                FROM classes c JOIN oeb o 
                                    ON o.ship=c.class 
                          LEFT JOIN ships s 
                                 ON c.class=s.class)
            )

(SELECT DISTINCT country, name --todos países VS todas batalhas possíveis
            FROM classes 
      CROSS JOIN (SELECT DISTINCT name FROM battles))
 MINUS
(SELECT DISTINCT country,battlename -- menos as batalhas que os países tem ships
            FROM tudotudo 
           WHERE shipname IN (SELECT ship FROM outcomes))

--132 1/2
WITH battles2 AS (
SELECT * FROM (
SELECT null name,null bdate FROM battles
UNION
SELECT * from battles) t
WHERE name IS NOT NULL)


SELECT 
     (CASE WHEN y=0 AND M=0 THEN null
      WHEN y=0 AND M<>0 THEN m||' m.'
      ELSE Y||' y., '||M||' m.'
      END
     ) age,
     d1,d2
FROM (

SELECT 

(CASE WHEN EXTRACT(month FROM date2)=EXTRACT(month FROM date1)
           AND EXTRACT(day FROM date1) = EXTRACT(day FROM date2) 
           THEN ROUND(EXTRACT(day from date2-date1)/365.2426) ELSE TRUNC(EXTRACT(day from date2-date1)/365.2426)
END) y,
MOD(TRUNC(months_between(date2,date1)),12) m,
to_char(date1,'yyyy-mm-dd') d1, 
to_char(date2,'yyyy-mm-dd') d2
FROM (
       SELECT bdate date1,
              NVL(LEAD(bdate,1)OVER(ORDER BY bdate),current_date) date2  
       FROM battles2) t 
)t2

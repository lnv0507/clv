select DISTINCT PROD_UNIT_AMT from  TB_PROD  where PROD_UNIT_AMT not in(Select B.PROD_UNIT_AMT
from TB_PROD  A, TB_PROD  B
where A.PROD_UNIT_AMT < B.PROD_UNIT_AMT);

select * from TB_PROD;

--C�u 9: cho s? 8988.80 vui l�ng xu?t ra ??nh d?ng $8,988.800
SELECT 
    TO_CHAR(8988.80, '$9,999.999')
FROM DUAL;

--C�u 10: cho s? 8988.80, 820988.80 vui l�ng xu?t ra ??nh d?ng $8,000.000, $820,000.000
SELECT
    TO_CHAR(TRUNC(8988.80, -3), '$9,999.999'),
    TO_CHAR(TRUNC(820988.80, -3), '$999,999.999')
FROM DUAL;

--C�u 12: Vi?t C�u SQL xu?t ra, Ng�y hi?n t?i, n�y h�m qua, ng�y mai
SELECT
    sysdate today,
    sysdate - 1 yesterday,
    sysdate + 1 tomorrow
FROM DUAL;

--c�u 13: 

SELECT 
    TO_CHAR(SYSDATE, 'YYYYMMDD') || NVL(LPAD(SUBSTR(MAX(ORD_DTTM),9,4) + 1,4,'0'), '0001') AS ORD_NO
FROM
    TB_ORD
WHERE
    ORD_DTTM LIKE TO_CHAR(SYSDATE, 'YYYYMMDD') || '%';

--C�u 14:
    --a)
        SELECT
            CUST_GRP_ID
        FROM
            (SELECT
                CUST_GRP_ID,
                COUNT(*)
                OVER(PARTITION BY CUST_GRP_ID) VOLUMN,
                CUST_GRP_HRCHY_CD
            FROM
                MDM_CUSTOMER
            WHERE
                CUST_GRP_HRCHY_CD != 'G'
            GROUP BY
                CUST_GRP_ID,
                CUST_GRP_HRCHY_CD
            )
        WHERE
             VOLUMN = 1;
    --b)
        -- c�ch 1
        SELECT DISTINCT (CUST_GRP_ID)
        FROM MDM_CUSTOMER 
        WHERE CUST_GRP_ID NOT IN 
                                (SELECT CUST_GRP_ID
                                FROM MDM_CUSTOMER
                                WHERE CUST_GRP_HRCHY_CD LIKE 'C')
        GROUP BY CUST_GRP_ID,
                CUST_GRP_HRCHY_CD;
        --c�ch 2
        WITH temp AS (
            SELECT
                cust_grp_id,
                COUNT(*)
                OVER(PARTITION BY cust_grp_id) volumn,
                cust_grp_hrchy_cd
            FROM
                mdm_customer
            GROUP BY
                cust_grp_id,
                cust_grp_hrchy_cd
            ORDER BY
                cust_grp_id,
                cust_grp_hrchy_cd
        )
        SELECT
            cust_grp_id
        FROM
            (
                SELECT
                    cust_grp_id,
                    cust_grp_hrchy_cd,
                    LEAD(cust_grp_id)
                    OVER(
                        ORDER BY
                            cust_grp_id
                    ) next_cust_grp_id,
                    LEAD(cust_grp_hrchy_cd)
                    OVER(
                        ORDER BY
                            cust_grp_id
                    ) next_cust_grp_hrchy_cd
                FROM
                    temp
                WHERE
                    volumn = 2
            )
        WHERE
                cust_grp_id = next_cust_grp_id
            AND cust_grp_hrchy_cd != 'C'
            AND next_cust_grp_id != 'C';
--C�u 15

    --C�ch 1

SELECT 
    MAX(TB_A.PROD_UNIT_AMT) max_amt,
    MIN(TB_A.PROD_UNIT_AMT) min_amt,
    AVG(TB_A.PROD_UNIT_AMT) avg,
    MAX(TB_B.PROD_NM) MAX_NAME 
FROM
    TB_PROD TB_A, (SELECT  PROD_NM  
                    from 
                        (SELECT PROD_NM, DENSE_RANK() OVER(ORDER BY PROD_UNIT_AMT DESC) MAX_RANK 
                        FROM TB_PROD 
                        WHERE  PROD_UNIT_AMT IS NOT NULL )
                    WHERE MAX_RANK = 1 ) TB_B;

    --C�ch 2
SELECT 
    MAX(PROD_UNIT_AMT) max_amt,
    MIN(PROD_UNIT_AMT) min_amt,
    AVG(PROD_UNIT_AMT) avg,
    MAX(PROD_NM) KEEP (DENSE_RANK LAST ORDER BY PROD_UNIT_AMT) AS max_name
FROM
    TB_PROD
WHERE PROD_UNIT_AMT IS NOT NULL;

--C�u 16
--a)
SELECT *
FROM
    (SELECT PRO_CD, DENSE_RANK() OVER(ORDER BY COUNT(*) DESC) AS TOP
    FROM TB_ORD
    GROUP BY PRO_CD)
WHERE TOP <4 AND ROWNUM <4;
--b)
SELECT
    cust_no,
    ord_dttm,
    ord_no,
    pro_cd
FROM
    (
        SELECT
            cust_no,
            ord_dttm,
            ord_no,
            pro_cd,
            ROW_NUMBER()
            OVER(PARTITION BY cust_no
                 ORDER BY
                     ord_dttm DESC
            ) rank
        FROM
            tb_ord
    ) temp
WHERE
    temp.rank = 1;

--C) Vi?t c�u SQL report xem trong th�ng 06, 07, 08, 09 c?a 2019 s?n ph?m c� m� code l� 00001 b�n ?c bao nhi�u c�i.
WITH report AS (
    SELECT
        '201906' AS dt
    FROM
        dual
    UNION ALL
    SELECT
        '201907' AS dt
    FROM
        dual
    UNION ALL
    SELECT
        '201908' AS dt
    FROM
        dual
    UNION ALL
    SELECT
        '201909' AS dt
    FROM
        dual
)
SELECT
    report.dt,
    nvl(pro_cd,'00001') pro_cd,
    nvl(ord.total, 0) total
FROM
    report 
    LEFT JOIN (
        SELECT
            pro_cd,
            substr(ord_dttm, 1, 6) AS ord_dttm,
            COUNT(*) total
        FROM
            tb_ord
        WHERE
            pro_cd = '00001'
        GROUP BY
            pro_cd,
            substr(ord_dttm, 1, 6)) ord 
    ON report.dt = ord.ord_dttm;

--D) Gi? s? l�c ??u s?n ph?n 00001 c� 100 c�i, vi?t report ?? t�nh s? l??ng remain theo th�ng 06, 07, 08, 09
WITH report AS (
    SELECT
        '201906' AS dt
    FROM
        dual
    UNION ALL
    SELECT
        '201907' AS dt
    FROM
        dual
    UNION ALL
    SELECT
        '201908' AS dt
    FROM
        dual
    UNION ALL
    SELECT
        '201909' AS dt
    FROM
        dual
)
SELECT
    report.dt, 
    nvl(ord.total, 0) total,
    100 - nvl(SUM(ord.total) OVER(PARTITION BY ord.pro_cd ORDER BY report.dt), 0) AS remain
FROM
    report
    LEFT JOIN (
        SELECT
            pro_cd,
            substr(ord_dttm, 1, 6) AS ord_dttm, 
            COUNT(*) total
        FROM
            tb_ord
        WHERE
            pro_cd = '00001'
        GROUP BY
            pro_cd,
            substr(ord_dttm, 1, 6)) ord 
    ON report.dt = ord.ord_dttm;
    
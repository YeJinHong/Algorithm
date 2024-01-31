SELECT J.FLAVOR
FROM
(
SELECT SHIPMENT_ID, FLAVOR, SUM(TOTAL_ORDER) TOTAL_ORDER
FROM JULY
GROUP BY FLAVOR
    ) J
JOIN
FIRST_HALF FH
ON J.FLAVOR = FH.FLAVOR
ORDER BY (J.TOTAL_ORDER+FH.TOTAL_ORDER) DESC
LIMIT 3
;
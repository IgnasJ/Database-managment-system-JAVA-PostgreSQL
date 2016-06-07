CREATE VIEW igja2281.AutoInfo AS
SELECT gamintojas, marke, metai, valstybinis_numeris, kaina 
FROM modeliai AS A
	INNER JOIN automobiliai AS B
		ON b.modelis = a.id
GROUP BY b.valstybinis_numeris, a.id;

SELECT * FROM AutoInfo;


CREATE VIEW igja2281.KlientuNuomos AS
SELECT vardas, pavarde, paimta, grazinta, gamintojas, marke, metai, valstybinis_numeris, kaina
FROM klientai AS A
	INNER JOIN nuomos AS B
		ON b.klientas = a.ak
	INNER JOIN AutoInfo as C
		ON b.automobilis = c.valstybinis_numeris;

SELECT * FROM KlientuNuomos;

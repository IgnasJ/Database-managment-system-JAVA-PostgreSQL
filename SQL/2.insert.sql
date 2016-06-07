INSERT INTO Klientai VALUES('39510102889', 'Tomas', 'Tominskas');
INSERT INTO Klientai VALUES('48508097779', 'Ona', 'Onaitiene', '+37062678068');
INSERT INTO Klientai VALUES('37508097779', 'Algis', 'Toskis', '+37062677224', 'Jurginu g. 3, Vilnius');
INSERT INTO Klientai VALUES('44404044444', 'Jonė', 'Bajorinė', '+37062677000', 'Jurginu g. 3, Vilnius');


INSERT INTO Modeliai VALUES(default, 'Opel', 'Ascona', '1995');
INSERT INTO Modeliai VALUES(default, 'Audi', '100', '1997');
INSERT INTO Modeliai VALUES(default, 'Audi', 'A6', '2004');
INSERT INTO Modeliai VALUES(default, 'Toyota', 'Avensis', '2004');

INSERT INTO Automobiliai VALUES('ABC123', 1, 140);
INSERT INTO Automobiliai VALUES('ABC124', 1, 150);
INSERT INTO Automobiliai VALUES('ABC125', 1, 160);
INSERT INTO Automobiliai VALUES('AUA789', 2, 210);
INSERT INTO Automobiliai VALUES('HUB007', 3, 220);
INSERT INTO Automobiliai VALUES('AGC111', 2, 230);

INSERT INTO Nuomos VALUES(default, '39510102889', 'ABC123', '2016-04-23', '2016-05-01');
INSERT INTO Nuomos VALUES(default, '37508097779', 'AUA789', '2016-05-13');
INSERT INTO Nuomos VALUES(default, '37508097779', 'HUB007', '2016-05-19');
INSERT INTO Nuomos VALUES(default, '39510102889', 'ABC124', '2016-05-19');














-- Negalima nes grazinimo data > nei siandien
INSERT INTO Nuomos VALUES(default, '48508097779', 'ABC125', '2015-05-23', '2016-05-16'); 

--EXCEPTION "Vienas automobilis, negali buti išnuomotas keliems žmonėms, kol jo negrąžino"
INSERT INTO Nuomos VALUES(default, '39510102889', 'HUB007', '2016-05-13');
--EXCEPTION "Negalima išsinuomoti atgaline data"
INSERT INTO Nuomos VALUES(default, '39510102889', 'AGC111', '2016-05-12');



SELECT * 
FROM Klientai;

SELECT *
FROM Modeliai;

SELECT *
FROM Automobiliai;

SELECT *
FROM Nuomos;
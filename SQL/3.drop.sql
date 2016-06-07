/* DROP VIEWS */

DROP VIEW igja2281.AutoInfo;
DROP VIEW igja2281.KlientuNuomos;

/* DROP INDEX */

DROP INDEX kliento_index;
DROP INDEX telefonas;

/* DROP TRIGGERS */

DROP TRIGGER atgalineData ON igja2281.Nuomos;
DROP FUNCTION neleistiAtgalinesDatos();

DROP TRIGGER paimtaTaPatiAuto ON igja2281.Nuomos;
DROP FUNCTION neleistiIssinuomotiKolNegrazintasAuto();


/* Drop Tables */

DROP TABLE Klientai CASCADE;
DROP TABLE Modeliai CASCADE;
DROP TABLE Automobiliai CASCADE;
DROP TABLE Nuomos;
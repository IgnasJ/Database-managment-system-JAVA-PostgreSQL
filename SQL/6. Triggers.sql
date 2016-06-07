--Neleisti issinuomoti atgaline data
CREATE FUNCTION neleistiAtgalinesDatos() RETURNS "trigger" AS 
$$
	BEGIN
		IF NEW.paimta < CURRENT_DATE
			THEN RAISE EXCEPTION 'Negalima išsinuomoti atgaline data!';
		END IF;
		RETURN NEW;
	END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER atgalineData
BEFORE INSERT ON igja2281.Nuomos
FOR EACH ROW EXECUTE PROCEDURE neleistiAtgalinesDatos();

--Neleisti issinuomoti to paties auto jei jis nera grazintas
CREATE FUNCTION neleistiIssinuomotiKolNegrazintasAuto() RETURNS "trigger" AS 
$$
	BEGIN
		IF (SELECT COUNT(*) FROM igja2281.Nuomos
			WHERE (grazinta IS NULL AND  automobilis = NEW.automobilis OR igja2281.Nuomos.paimta IS NULL)) > 0  
		THEN
			RAISE EXCEPTION 'Vienas automobilis, negali buti išnuomotas keliems žmonėms, kol jo negrąžino!';
		END IF;
		RETURN NEW;
	END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER paimtaTaPatiAuto
BEFORE INSERT ON igja2281.Nuomos
FOR EACH ROW EXECUTE PROCEDURE neleistiIssinuomotiKolNegrazintasAuto();

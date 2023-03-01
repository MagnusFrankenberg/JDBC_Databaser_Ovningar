use Tomteverkstad;
 
 -- stored procedure "addTomte" -------------------------------------
drop procedure if exists läggTillTomte;
delimiter //
create procedure läggTillTomte(IN tomteNamn varchar(100), IN chefNamn varchar(100), IN tomteRoll int,IN säkerhetsKL int, OUT wasAdded boolean)
begin

	declare nytomteID int default 0;
    declare chefID int default 0;
    declare INPUT_ERROR_EXCEPTION condition for sqlstate '45000';
    
    declare exit handler for INPUT_ERROR_EXCEPTION
    begin 
	rollback;
    resignal;
	end;
    
    declare exit handler for SQLEXCEPTION
    begin 
	rollback;
    resignal set message_text = 'An error occurred, execution is halted and changes are rolled back';
	end;
    
    start transaction;
    set wasAdded = 0;
    
    -- om tomteNamn redan finns, signalera fel på detta.
    if exists (select tomte.namn from tomte where namn=tomteNamn) then      
    signal INPUT_ERROR_EXCEPTION
    set message_text = 'En Tomte med detta namn finns redan inlagd, endast unika namn tillåtna';
    end if; 
    
    -- om chefNamn inte är en chefsTomte, signalera fel på detta.
    if not exists (select tomte.namn from tomte join Chefstomte on Chefstomte.tomteid=tomte.id and tomte.namn = chefNamn) then      
    signal INPUT_ERROR_EXCEPTION
    set message_text = 'Kunde inte hitta någon ChefsTomte med angivet namn, operationen avbruten';
    end if;
    
    -- om nya tomten är en underrättelsetomte, kolla om säkerhetsklassen är korrekt (1,2 eller 3)
    if tomteRoll = 2 and säkerhetsKL not in (1,2,3) then
    -- if säkerhetsKL not in (1,2,3) then
    signal INPUT_ERROR_EXCEPTION
    set message_text = 'Angiven säkerhetsklass för Underrättelsetomte ej giltig. Ange klass 1,2 eller 3';
    end if;

    
    
     -- Lägg in tomte i Tomtetabell
    insert into Tomte(namn) 
	values (tomteNamn);
    select LAST_INSERT_ID() into nytomteID;
    
    -- Lägg in Tomteroll
    case tomteRoll
    -- Chefstomte:
    when 1 then insert into Chefstomte(tomteid) values (nytomteID);
    -- Underrättelsetomte:
    when 2 then insert into Underrättelsetomte(tomteid, säkerhetsklass) values (nytomteID, säkerhetsKL);
    -- Tillverkningstomte:
    when 3 then insert into Tillverkningstomte(tomteid) values (nytomteID);
    end case;
    
    -- Hitta Chefens id
    select tomte.id into chefID from tomte join Chefstomte on Chefstomte.tomteid=tomte.id and tomte.namn = chefNamn;
    
    -- Lägg in i Tomtehierarki
    insert into Tomtehierarki(underordnad, överordnad) values (nytomteID,chefID);
    
    -- tomte added successfully?
    if exists (select tomte.namn from tomte where namn=tomteNamn) then      
    set wasAdded = 1;
    end if; 

commit;

END//
DELIMITER ;

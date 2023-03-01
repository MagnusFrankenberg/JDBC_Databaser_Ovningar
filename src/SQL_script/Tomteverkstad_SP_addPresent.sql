 use Tomteverkstad;
 
 -- stored procedure "läggTillPresent" -------------------------------------
drop procedure if exists läggTillPresent;
delimiter //
create procedure läggTillPresent(IN presentNamn varchar(100), IN tillverkarID int, OUT wasAdded boolean)
begin

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
    
    -- om present redan finns, signalera fel på detta.
    if exists (select present.namn from present where namn=presentNamn) then      
    signal INPUT_ERROR_EXCEPTION
    set message_text = 'Denna present finns redan i Tomtens databas och kunde därför inte läggas till';
    end if; 
    
    -- om tillverkarID inte existerar, signalera fel på detta.
    if not exists (select tillverkningstomte.tomteid from tillverkningstomte where tomteid=tillverkarID) then      
    signal INPUT_ERROR_EXCEPTION
    set message_text = 'ID för Tillverkningstomte kunde inte hittas, presenten har inte lagts till';
    end if;
    
    -- Lägg in present och tillverkningstomteid
    insert into Present(namn,tillverkningstomteID) 
	values (presentNamn,tillverkarID);
    
    if exists (select present.namn from present where namn=presentNamn) then      
    set wasAdded = 1;
    end if; 

commit;

END//
DELIMITER ;



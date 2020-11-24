//Ler arquivo JSON e insere na tabela

		//Criação Base Estados
#
WITH arquivo_json_state( doc ) AS (
VALUES (pg_read_file('C:\temp\workspace-sts\petsCare\conf\json\state.json', 0 ,1000000000 ) ::json))
  
INSERT INTO  tb_state ( id_state, initials, name )
  SELECT
    rec.id_state, rec.initials, rec.name
  FROM
    arquivo_json_state AS arq
  CROSS JOIN 
     json_populate_recordset( NULL::tb_state, doc ) AS rec;
    
    
    
    
     //Criação da Base Cidades 
     
     
    WITH arquivo_json_city( doc ) AS (
VALUES (pg_read_file('C:\temp\workspace-sts\petsCare\conf\json\city.json', 0 ,1000000000 ) ::json))
  
INSERT INTO  tb_city ( fk_state, id_city, name , last_update )
  SELECT
    rec.fk_state, rec.id_city, rec.name, NOW()
  FROM
    arquivo_json_city AS arq
  CROSS JOIN 
     json_populate_recordset( NULL::tb_city, doc ) AS rec;
     
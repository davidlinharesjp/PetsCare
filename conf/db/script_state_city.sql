//Ler arquivo JSON e insere na tabela


#
WITH arquivo_json_state( doc ) AS (
VALUES (pg_read_file('C:\Users\Public\3012978-2e43be5f86eef95b915c1c804ccc86dc9790a50a\state.json', 0 ,1000000000 ) ::json))
  
INSERT INTO  tb_state ( id_state, initials, name )
  SELECT
    rec.id_state, rec.initials, rec.name
  FROM
    arquivo_json_state AS arq
  CROSS JOIN 
     json_populate_recordset( NULL::tb_state, doc ) AS rec;
     
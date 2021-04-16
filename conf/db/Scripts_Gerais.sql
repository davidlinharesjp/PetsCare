//Script de Pegar proxima sequencia da tabela


//Script Atualização de Preço
CREATE OR REPLACE FUNCTION ajuste_price_tb_product(real ) RETURNS SETOF tb_product AS
$BODY$
DECLARE
    c tb_product%rowtype;
BEGIN
    FOR c IN
      SELECT * FROM tb_product
    LOOP
        -- can do some processing here
     UPDATE tb_product SET vl_price = (vl_price + (vl_price / $1)), ts_last_update = NOW() where id_product = c.id_product ;

  END LOOP;
  RETURN QUERY	(Select * from tb_product);
END
$BODY$
LANGUAGE plpgsql;
END;

SELECT * FROM ajuste_price_tb_product(10);

//Script Redução de Valores
CREATE OR REPLACE FUNCTION reduzir_price_tb_product(real ) RETURNS SETOF tb_product AS
$BODY$
DECLARE
    c tb_product%rowtype;
BEGIN
    FOR c IN
      SELECT * FROM tb_product
    LOOP
     UPDATE tb_product SET vl_price = (vl_price - (vl_price * $1 / 100)), ts_last_update = NOW() where id_product = c.id_product ;

  END LOOP;
  RETURN QUERY	(Select * from tb_product);
END
$BODY$
LANGUAGE plpgsql;
END;

SELECT * FROM reduzir_price_tb_product(10.5);

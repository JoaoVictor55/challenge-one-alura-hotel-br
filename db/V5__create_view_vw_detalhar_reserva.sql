use hotel_alura;

/*insert into tbl_nacionalidade(nac_nome) values("americano");

insert into tbl_hospede(hos_nome, hos_sobrenome, hos_data_nascimento, 
hos_nacionalidade, hos_telefone)
values("alex", "santos", "2000/06/12", 1, 1234);
*/

create or replace view vw_detalhar_reserva as
select res_id, res_data_entrada, res_valor, tbl_forma_pagamento.nome as forma_pagamento, tbl_forma_pagamento.id as pagamentoID,
data_saida, hos_id, hos_nome, hos_sobrenome, hos_data_nascimento, hos_nacionalidade,
nac_nome, hos_telefone  from tbl_reserva inner join tbl_forma_pagamento
on tbl_forma_pagamento.id = tbl_reserva.res_forma_pagamento
inner join vw_detalhar_hospede on vw_detalhar_hospede.hos_id = tbl_reserva.hos_id_tbl_hospede;
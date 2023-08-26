use hotel_alura;

alter table tbl_hospede 
drop constraint hospedeFK;

alter table tbl_hospede
add constraint hospedeFK foreign key(hos_nacionalidade)
references tbl_nacionalidade(nac_id)
on delete set null;

alter table tbl_nacionalidade
modify column nac_nome  varchar(50);
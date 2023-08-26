use hotel_alura;

begin;
CREATE TABLE IF NOT EXISTS tbl_nacionalidade(
	
    nac_id int not null auto_increment,
    nac_nome varchar(20) not null,
    constraint nacionalidadePK primary key(nac_id)
);

CREATE TABLE IF NOT EXISTS tbl_hospede (
	hos_id INT NOT NULL AUTO_INCREMENT,
	hos_nome VARCHAR(20) NOT NULL,
	hos_sobrenome VARCHAR(80) NOT NULL,
	hos_data_nascimento DATE NOT NULL,
	hos_nacionalidade int,
	hos_telefone VARCHAR(15),
	PRIMARY KEY(hos_id),
    constraint hospedeFK foreign key(hos_nacionalidade) references tbl_nacionalidade(nac_id)
);

CREATE TABLE IF NOT EXISTS tbl_reserva (
	res_id INT NOT NULL AUTO_INCREMENT,
	res_data_entrada DATE NULL,
	res_valor NUMERIC(9,2) NULL,
	res_forma_pagamento VARCHAR(50) NULL,
	hos_id_tbl_hospede INT NOT NULL,
	PRIMARY KEY(res_id),
	FOREIGN KEY(hos_id_tbl_hospede) REFERENCES tbl_hospede(hos_id)
);

commit;
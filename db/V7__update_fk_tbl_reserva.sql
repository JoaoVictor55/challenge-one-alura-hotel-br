
use hotel_alura;
alter table tbl_reserva
drop constraint tbl_reserva_ibfk_1;

alter table tbl_reserva
add constraint tbl_reserva_ibfk_1 foreign key(hos_id_tbl_hospede)
references tbl_hospede(hos_id)
on delete cascade;
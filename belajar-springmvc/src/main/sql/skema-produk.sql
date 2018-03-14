create table produk (
    id serial,
    kode varchar(20) not null,
    nama varchar(255) not null,
    harga decimal(19,2) not null
);


insert into produk (kode, nama, harga)
values ('P-001', 'Produk 001', 100001.00);
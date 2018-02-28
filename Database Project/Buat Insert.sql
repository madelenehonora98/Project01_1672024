INSERT INTO `userrole` (`idUserRole`,`Jabatan`) VALUES ('01','Owner');
INSERT INTO `userrole` (`idUserRole`,`Jabatan`) VALUES ('02','Cashier');

INSERT INTO `barang` (`KodeBarang`,`NamaBarang`,`HargaBeli`,`HargaJual`,`Stok`) VALUES ('A001','Tas Ransel',10000,30000,30);
INSERT INTO `barang` (`KodeBarang`,`NamaBarang`,`HargaBeli`,`HargaJual`,`Stok`) VALUES ('A002','Harddisk',80000,100000,2);
INSERT INTO `barang` (`KodeBarang`,`NamaBarang`,`HargaBeli`,`HargaJual`,`Stok`) VALUES ('A003','Monitor',100000,300000,5);
INSERT INTO `barang` (`KodeBarang`,`NamaBarang`,`HargaBeli`,`HargaJual`,`Stok`) VALUES ('A004','Mouse',20000,50000,10);

INSERT INTO `user` (`IdPengguna`,`NamaDepan`,`NamaBelakang`,`Alamat`,`NoTelepon`,`UserRole_idUserRole`,`Password`,`JenisKelamin`) VALUES ('1672011','Yoko','Wilyam','Jln. Babakan Jeruk','0822222222','02','1672011','Laki-laki');
INSERT INTO `user` (`IdPengguna`,`NamaDepan`,`NamaBelakang`,`Alamat`,`NoTelepon`,`UserRole_idUserRole`,`Password`,`JenisKelamin`) VALUES ('1672014','Lydia','Noviani','Jln. Babakan Jeruk','08111111','02','1672014','Perempuan');
INSERT INTO `user` (`IdPengguna`,`NamaDepan`,`NamaBelakang`,`Alamat`,`NoTelepon`,`UserRole_idUserRole`,`Password`,`JenisKelamin`) VALUES ('1672024','Madelene','Honora','Jln. Taman Lili Gardenia','0813207523','01','1672024','Perempuan');

INSERT INTO `dbpenjualan`.`notapenjualan` (`KodePenjualan`, `Nominal`, `TanggalPenjualan`, `User_IdPengguna`) VALUES ('1', '100000', '2013-04-15', '1672014');
INSERT INTO `dbpenjualan`.`notapenjualan` (`KodePenjualan`, `Nominal`, `TanggalPenjualan`, `User_IdPengguna`) VALUES ('2', '130000', '2015-12-01', '1672014');
INSERT INTO `dbpenjualan`.`notapenjualan` (`KodePenjualan`, `Nominal`, `TanggalPenjualan`, `User_IdPengguna`) VALUES ('3', '200000', '2016-10-03', '1672011');

INSERT INTO `barang_has_notapenjualan` (`Barang_KodeBarang`,`NotaPenjualan_KodePenjualan`,`JumlahBarangTerjual`,`HargaJualSaatIni`) VALUES ('A002',1,1,100000);
INSERT INTO `barang_has_notapenjualan` (`Barang_KodeBarang`,`NotaPenjualan_KodePenjualan`,`JumlahBarangTerjual`,`HargaJualSaatIni`) VALUES ('A002',2,1,100000);
INSERT INTO `barang_has_notapenjualan` (`Barang_KodeBarang`,`NotaPenjualan_KodePenjualan`,`JumlahBarangTerjual`,`HargaJualSaatIni`) VALUES ('A001',2,1,30000);
INSERT INTO `dbpenjualan`.`barang_has_notapenjualan` (`Barang_KodeBarang`, `NotaPenjualan_KodePenjualan`, `JumlahBarangTerjual`, `HargaJualSaatIni`) VALUES ('A002', '3', '1', '100000');
INSERT INTO `dbpenjualan`.`barang_has_notapenjualan` (`Barang_KodeBarang`, `NotaPenjualan_KodePenjualan`, `JumlahBarangTerjual`, `HargaJualSaatIni`) VALUES ('A004', '3', '2', '50000');


create database if not exists BD_01_06_21_pick7
default character set utf8mb4
default collate utf8mb4_general_ci;

use BD_01_06_21_pick7;

create table if not exists usuario(
idUsuario int(11) not null auto_increment primary key,
nomeUsuario varchar(20) not null,
senha varchar(60) not null,
tipoUsuario varchar(20) not null
);

insert into usuario(idUsuario, nomeUsuario, senha, tipoUsuario) values
(1, "admin", "21232f297a57a5a743894a0e4a801fc3", "Administrador");

#select * from usuario;
#drop table usuario;

create table if not exists sistema(
idSistema int(11) not null auto_increment primary key,
chaveSeguranca varchar(20) not null,
tipoUsuarioLogado varchar(20) not null,
idArquivo int 
);

insert into sistema(idSistema, chaveSeguranca, tipoUsuarioLogado, idArquivo) values
(1, "admin123", "Administrador", 0);

#select * from sistema;
#drop table sistema;

create table if not exists cliente(
idCliente int(11) not null auto_increment primary key,
cpfCliente  varchar(14) not null,
nomeCliente varchar(60) not null,
telefone varchar(16) not null,
eMail varchar(60),
sexo varchar(20),
dataNascimento varchar(20)
);

#select * from cliente;
#drop table cliente;

create table if not exists quarto(
idQuarto int(11) not null auto_increment primary key,
statusQuarto varchar(20) not null,
categoriaQuarto varchar(20) not null,
qtdCamas int not null,
tipoCama varchar(20) not null,
anotacoes varchar(50)
)DEFAULT CHARSET=latin1;

#select * from quarto;
#drop table quarto;

insert into quarto(idQuarto, statusQuarto, categoriaQuarto, qtdCamas, tipoCama, anotacoes) values
(1, "Livre", "Quarto-individual", 1, "Solteiro","Quarto com vista ao mar."),
(2, "Ocupado", "Quarto-duplo", 2, "Solteiro", "**"),
(3, "Reservado", "Quarto-casal", 1, "Casal", "**"),
(4, "Em manutenção", "Quarto-solteiro", 1, "Solteiro", "**"),
(5, "Livre", "Quarto-solteiro", 1, "Solteiro", "**");

create table if not exists reserva(
idReserva int(11) not null auto_increment primary key,
-- dataInicioReserva date not null,
-- dataFimReserva date not null,
dataInicioReserva varchar(10) not null,
dataFimReserva varchar(10) not null,
idQuarto int(11) not null,
categoriaQuarto varchar(20) not null,
cpfCliente  varchar(14) not null
-- foreign key (idQuarto) references quarto(idQuarto),
-- foreign key (idCliente) references cliente(idCliente)
);

insert into reserva(idReserva, dataInicioReserva, dataFimReserva, idQuarto, categoriaQuarto, cpfCliente) values
(1, "20/06/0009", "2021-06-10", 1, "bom", "123.456.789.10");

#select * from reserva;
#drop table reserva;


create table if not exists hospedagem(
idHospedagem int(11) not null auto_increment primary key,
cpfCliente  varchar(14) not null,
idQuarto int(11) null,
idReserva int(11) null
);

#select * from hospedagem;
#drop table hospedagem; 
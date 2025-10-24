create table if not exists LOGS (
    ID bigint auto_increment primary key,
    FECHA_HORA datetime not null,
    MENSAJE varchar(200) not null
)^;
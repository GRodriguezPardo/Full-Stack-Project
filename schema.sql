
    create table Asociacion (
        id bigint not null,
        primary key (id)
    )

    create table Asociacion_PublicacionInteresadoEnAdopcion (
        Asociacion_id bigint not null,
        publicacionInteresadoEnAdopcion_id bigint not null
    )

    create table Asociacion_PublicacionMascotaEnAdopcion (
        Asociacion_id bigint not null,
        publicacionesDeMascotasEnAdopcion_id bigint not null
    )

    create table Asociacion_PublicacionMascotaPerdida (
        Asociacion_id bigint not null,
        publicacionesDeMascotasPerdidas_id bigint not null
    )

    create table Contacto (
        id bigint not null,
        email varchar(255),
        nombreApellido varchar(255),
        telefono varchar(255),
        primary key (id)
    )

    create table PersistenceId (
        id bigint not null,
        primary key (id)
    )

    create table Persona (
        id bigint not null,
        fechaNacimiento varbinary(255),
        nombreYApellido varchar(255),
        primary key (id)
    )

    create table PublicacionInteresadoEnAdopcion (
        id bigint not null,
        primary key (id)
    )

    create table PublicacionMascotaEnAdopcion (
        id bigint not null,
        primary key (id)
    )

    create table PublicacionMascotaPerdida (
        id bigint not null,
        aprobado boolean,
        primary key (id)
    )

    create table Rescatista (
        id bigint not null,
        fecha varbinary(255),
        primary key (id)
    )

    alter table Asociacion_PublicacionInteresadoEnAdopcion 
        add constraint UK_hg6pldtmjnuclnqbfgq4tcj79  unique (publicacionInteresadoEnAdopcion_id)

    alter table Asociacion_PublicacionMascotaEnAdopcion 
        add constraint UK_93ta7rnbj8vk5bigflydjsdy0  unique (publicacionesDeMascotasEnAdopcion_id)

    alter table Asociacion_PublicacionMascotaPerdida 
        add constraint UK_ehu8fj87j1cvyeuoxxecr0qwx  unique (publicacionesDeMascotasPerdidas_id)

    alter table Asociacion_PublicacionInteresadoEnAdopcion 
        add constraint FK_hg6pldtmjnuclnqbfgq4tcj79 
        foreign key (publicacionInteresadoEnAdopcion_id) 
        references PublicacionInteresadoEnAdopcion

    alter table Asociacion_PublicacionInteresadoEnAdopcion 
        add constraint FK_gs2dspx9hjfjra67tq3vc25gr 
        foreign key (Asociacion_id) 
        references Asociacion

    alter table Asociacion_PublicacionMascotaEnAdopcion 
        add constraint FK_93ta7rnbj8vk5bigflydjsdy0 
        foreign key (publicacionesDeMascotasEnAdopcion_id) 
        references PublicacionMascotaEnAdopcion

    alter table Asociacion_PublicacionMascotaEnAdopcion 
        add constraint FK_lqlssref0xmjv44s523qv0431 
        foreign key (Asociacion_id) 
        references Asociacion

    alter table Asociacion_PublicacionMascotaPerdida 
        add constraint FK_ehu8fj87j1cvyeuoxxecr0qwx 
        foreign key (publicacionesDeMascotasPerdidas_id) 
        references PublicacionMascotaPerdida

    alter table Asociacion_PublicacionMascotaPerdida 
        add constraint FK_6mr14wfsvnnmtc6sq9vm6687h 
        foreign key (Asociacion_id) 
        references Asociacion

    create sequence hibernate_sequence start with 1

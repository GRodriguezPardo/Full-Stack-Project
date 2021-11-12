
    create table Admin (
        id bigint generated by default as identity (start with 1),
        clave varchar(255),
        usuario varchar(255),
        primary key (id)
    )

    create table Asociacion (
        id bigint generated by default as identity (start with 1),
        latitud double,
        longitud double,
        primary key (id)
    )

    create table Asociacion_Pregunta (
        Asociacion_id bigint not null,
        preguntas_id bigint not null
    )

    create table Caracteristica (
        id bigint generated by default as identity (start with 1),
        valor varchar(255),
        primary key (id)
    )

    create table Contacto (
        id bigint generated by default as identity (start with 1),
        email varchar(255),
        nombreApellido varchar(255),
        telefono varchar(255),
        primary key (id)
    )

    create table Duenio (
        id bigint generated by default as identity (start with 1),
        persona_id bigint,
        primary key (id)
    )

    create table Duenio_Mascota (
        Duenio_id bigint not null,
        mascotas_id bigint not null
    )

    create table JavaXMail (
        id bigint generated by default as identity (start with 1),
        clave varchar(255),
        remitente varchar(255),
        primary key (id)
    )

    create table Mascota (
        id bigint generated by default as identity (start with 1),
        apodo varchar(255),
        descripcion varchar(255),
        edad smallint,
        especie integer,
        nombre varchar(255),
        sexo integer,
        tamanio integer,
        primary key (id)
    )

    create table MascotaPerdida (
        id bigint generated by default as identity (start with 1),
        descripcionEstado varchar(255),
        latitud double,
        longitud double,
        primary key (id)
    )

    create table MedioNotificacion (
        DTYPE varchar(31) not null,
        id bigint generated by default as identity (start with 1),
        primary key (id)
    )

    create table Persona (
        id bigint generated by default as identity (start with 1),
        fechaNacimiento varbinary(255),
        nombreYApellido varchar(255),
        primary key (id)
    )

    create table Persona_Contacto (
        Persona_id bigint not null,
        contactos_id bigint not null
    )

    create table Persona_MedioNotificacion (
        Persona_id bigint not null,
        mediosNotificacion_id bigint not null
    )

    create table PosiblesCaracteristicas (
        id bigint generated by default as identity (start with 1),
        primary key (id)
    )

    create table Pregunta (
        id bigint generated by default as identity (start with 1),
        cuerpoDuenio varchar(255),
        cuerpoInteresado varchar(255),
        visible boolean not null,
        primary key (id)
    )

    create table Pregunta_opciones (
        Pregunta_id bigint not null,
        opciones varchar(255)
    )

    create table PublicacionInteresadoEnAdopcion (
        id bigint generated by default as identity (start with 1),
        persona_id bigint,
        Asociacion_Id bigint,
        primary key (id)
    )

    create table PublicacionInteresadoEnAdopcion_Respuesta (
        PublicacionInteresadoEnAdopcion_id bigint not null,
        respuestas_id bigint not null
    )

    create table PublicacionMascotaEnAdopcion (
        id bigint generated by default as identity (start with 1),
        mascota_id bigint,
        Asociacion_Id bigint,
        primary key (id)
    )

    create table PublicacionMascotaEnAdopcion_Respuesta (
        PublicacionMascotaEnAdopcion_id bigint not null,
        respuestas_id bigint not null
    )

    create table PublicacionMascotaPerdida (
        id bigint generated by default as identity (start with 1),
        aprobado boolean,
        rescatista_id bigint,
        Asociacion_Id bigint,
        primary key (id)
    )

    create table Rescatista (
        id bigint generated by default as identity (start with 1),
        fecha date,
        mascota_id bigint,
        persona_id bigint,
        primary key (id)
    )

    create table Respuesta (
        id bigint generated by default as identity (start with 1),
        respuesta varchar(255),
        pregunta_id bigint,
        primary key (id)
    )

    create table TwilioJava (
        id bigint generated by default as identity (start with 1),
        accountSid varchar(255),
        authToken varchar(255),
        senderNumber varchar(255),
        primary key (id)
    )

    create table Usuario (
        id bigint generated by default as identity (start with 1),
        clave varchar(255),
        usuario varchar(255),
        duenio_id bigint,
        primary key (id)
    )

    create table Voluntario (
        id bigint generated by default as identity (start with 1),
        clave varchar(255),
        usuario varchar(255),
        asociacion_id bigint,
        primary key (id)
    )

    create table caracteristicas_mapping (
        mascota_id bigint not null,
        caracteristicas_id bigint not null,
        nombre_caracteristica varchar(255) not null,
        primary key (mascota_id, nombre_caracteristica)
    )

    alter table Asociacion_Pregunta 
        add constraint UK_fs7dv9osx6to8wag1aj5uufde  unique (preguntas_id)

    alter table Duenio_Mascota 
        add constraint UK_qpipeipxqev8ej5l4ncemhtiu  unique (mascotas_id)

    alter table Persona_Contacto 
        add constraint UK_jm11es923mct12lt8o2g7lcgo  unique (contactos_id)

    alter table PublicacionInteresadoEnAdopcion_Respuesta 
        add constraint UK_fx6g0ir1jr4g3vapf9h5h9ba4  unique (respuestas_id)

    alter table PublicacionMascotaEnAdopcion_Respuesta 
        add constraint UK_gydw5ylm6yg6txtkogo5gbjm  unique (respuestas_id)

    alter table caracteristicas_mapping 
        add constraint UK_pfa96oi4nhhv2l1jw817gy9e8  unique (caracteristicas_id)

    alter table Asociacion_Pregunta 
        add constraint FK_fs7dv9osx6to8wag1aj5uufde 
        foreign key (preguntas_id) 
        references Pregunta

    alter table Asociacion_Pregunta 
        add constraint FK_1oygc8lawdiinkftl9tffpsdu 
        foreign key (Asociacion_id) 
        references Asociacion

    alter table Duenio 
        add constraint FK_ljqg8vj3y1uscel2sdbl6t8ps 
        foreign key (persona_id) 
        references Persona

    alter table Duenio_Mascota 
        add constraint FK_qpipeipxqev8ej5l4ncemhtiu 
        foreign key (mascotas_id) 
        references Mascota

    alter table Duenio_Mascota 
        add constraint FK_hynmo2ivhse330d9xq596389b 
        foreign key (Duenio_id) 
        references Duenio

    alter table Persona_Contacto 
        add constraint FK_jm11es923mct12lt8o2g7lcgo 
        foreign key (contactos_id) 
        references Contacto

    alter table Persona_Contacto 
        add constraint FK_q2vaw4wbtycf5iqomsy6wq7me 
        foreign key (Persona_id) 
        references Persona

    alter table Persona_MedioNotificacion 
        add constraint FK_t5cnlu1pcxpvmsimel6fme5u2 
        foreign key (mediosNotificacion_id) 
        references MedioNotificacion

    alter table Persona_MedioNotificacion 
        add constraint FK_p0fh6l6hduq439scc78v7u3et 
        foreign key (Persona_id) 
        references Persona

    alter table Pregunta_opciones 
        add constraint FK_mw94rc1yg3ip98pc65sp2iq3e 
        foreign key (Pregunta_id) 
        references Pregunta

    alter table PublicacionInteresadoEnAdopcion 
        add constraint FK_aemp1m4f6gmptlkapmxakfot 
        foreign key (persona_id) 
        references Persona

    alter table PublicacionInteresadoEnAdopcion 
        add constraint FK_nvmu8qawqy16ombfxt0ujbdoe 
        foreign key (Asociacion_Id) 
        references Asociacion

    alter table PublicacionInteresadoEnAdopcion_Respuesta 
        add constraint FK_fx6g0ir1jr4g3vapf9h5h9ba4 
        foreign key (respuestas_id) 
        references Respuesta

    alter table PublicacionInteresadoEnAdopcion_Respuesta 
        add constraint FK_2cra12knmj8botih68u89q2vc 
        foreign key (PublicacionInteresadoEnAdopcion_id) 
        references PublicacionInteresadoEnAdopcion

    alter table PublicacionMascotaEnAdopcion 
        add constraint FK_35uijj9sam9y736yed0u0mcd8 
        foreign key (mascota_id) 
        references Mascota

    alter table PublicacionMascotaEnAdopcion 
        add constraint FK_lhw7wsilbuca3vwb54qqbf60h 
        foreign key (Asociacion_Id) 
        references Asociacion

    alter table PublicacionMascotaEnAdopcion_Respuesta 
        add constraint FK_gydw5ylm6yg6txtkogo5gbjm 
        foreign key (respuestas_id) 
        references Respuesta

    alter table PublicacionMascotaEnAdopcion_Respuesta 
        add constraint FK_nlftof07npfq4ydv1467b3ku4 
        foreign key (PublicacionMascotaEnAdopcion_id) 
        references PublicacionMascotaEnAdopcion

    alter table PublicacionMascotaPerdida 
        add constraint FK_idcp4b6p9hxo3gg1qy9m57rwq 
        foreign key (rescatista_id) 
        references Rescatista

    alter table PublicacionMascotaPerdida 
        add constraint FK_8n6obhvp2l3g1g2vuha9mlpcw 
        foreign key (Asociacion_Id) 
        references Asociacion

    alter table Rescatista 
        add constraint FK_au1kf4s3opx6jpvru34m1b8fx 
        foreign key (mascota_id) 
        references MascotaPerdida

    alter table Rescatista 
        add constraint FK_4glsf37c37rs0wotp5xbj807m 
        foreign key (persona_id) 
        references Persona

    alter table Respuesta 
        add constraint FK_6u4tlwh295l76h26254mmulpi 
        foreign key (pregunta_id) 
        references Pregunta

    alter table Usuario 
        add constraint FK_7i8ibv9bwq0m1pv618xfenaw8 
        foreign key (duenio_id) 
        references Duenio

    alter table Voluntario 
        add constraint FK_15jg7c6d6l9uuqpibrvatuw9d 
        foreign key (asociacion_id) 
        references Asociacion

    alter table caracteristicas_mapping 
        add constraint FK_pfa96oi4nhhv2l1jw817gy9e8 
        foreign key (caracteristicas_id) 
        references Caracteristica

    alter table caracteristicas_mapping 
        add constraint FK_tpjprt1to2n1thsr1e741bu80 
        foreign key (mascota_id) 
        references Mascota

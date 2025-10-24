create procedure if not exists CARGAR_DATOS_INICIALES()
begin
    if not exists (select * from LOGS) then

        -- CONSULTORES --
    
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('admin','$2a$12$YfHyOElqrEy6Cbh41wxbBeNtfzBXYk4nPshA/2LTAhVBvwQGA2ZZa',1); -- admin --
        insert into CONSULTORES(NOMBRE_USUARIO) values('admin');

        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('jacosta','$2a$12$00VVHk5XkNU0oGsq.gDII.IGlXYWYwLPHKcQUbLKXLQ8UacYLvxay',1); -- javier123 --
        insert into CONSULTORES(NOMBRE_USUARIO) values('jacosta');
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('gcabrera','$2a$12$aVyFYauz.JZZwBYuxJm5i./LXQV.m52FkPYqU6nEuIEVWlorv2Pcm',1); -- cabrera123 --
        insert into CONSULTORES(NOMBRE_USUARIO) values('gcabrera');
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('wiriarte','$2a$12$nQfR1LTk6MyAH2RCeMLNKOt4r0lKZXQ0phuNjsOxb4pW6s8QdEyWi',1); -- wanda123 --
        insert into CONSULTORES(NOMBRE_USUARIO) values('wiriarte');
        
        -- ROLES --

        insert into roles(nombre_rol) 
        values
            ('consultor'),
            ('cliente'),
            ('postulante');
            

        -- CLIENTES --

        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('black_dog','$2a$12$fXzuDZhU/q6RmkljH.v0N.we4zeG7JRi7HyJYwEuDG5txv7lYmMVC',1); -- black123 --
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('black_dog','217425400015','Black Dog Electronics','https://www.bde.com.uy/',1);

        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('gianni','$2a$12$q2s9dQ0rFsU6bp/CLoz6UuqKJ8uKZND9FpI9mq2/g8/NdnDPMN7u.',1); -- giani123 --
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('gianni','110050760010','Gianni','https://www.gianni.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('global_sports','$2a$12$j3ATnad15nRBdiobrLF3DOQUDD0WzCaEPjAvn7cGbIHfU4EPHrWwS',1); -- global123 --
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('global_sports','214468370016','GLOBAL SPORTS','https://www.globalsports.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('biko','$2a$12$69SjGlRKjx.QY35yXuateuPq/g9YSeoHQ0dk4wM2IdnlGTb5jqQku',1); -- biko123 --
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('biko','215550120018','BIKO','https://www.biko.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('tanirel','$2a$12$yr95SksKieSS1a3TjutB7.TLP5IHuGUUKgHOBLKbvS/13iNAu/2My',1); -- tanirel --
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('tanirel','214114060013','Tanirel','https://tanirel.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('abitab','4shXFkUVh',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('abitab','211428940011','Abitab','https://www.abitab.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('zona_laptop','JF4had5Vzy',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('zona_laptop','215531060013','Zona Laptop','https://zonalaptop.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('universo_binario','eYpEJkEC3',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('universo_binario','216359520013','Universo Binario','https://universobinario.com/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('tienda_inglesa','D27tbS3e7G',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('tienda_inglesa','210094030014','Tienda Inglesa','https://www.tiendainglesa.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('leopoldo_gross','LzDV6d2PE',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('leopoldo_gross','210000830011','Leopoldo Gross & Asociados','https://leopoldogross.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('opera','3Xk97gnfYAT',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('opera','210066090016','La Opera','https://laopera.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('agroenfoque','4YUE4QGbUq',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('agroenfoque','214607570019','AGROENFOQUE','https://www.agroenfoque.com.uy/',1);
  
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('apiter','VdbY3PFq6L',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('apiter','210774960019','Apiter','https://apiter.com/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('audical','esJo3DLP',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('audical','213518030010','Audical','https://www.audical.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('banifox','AFaDj3VtG5C',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('banifox','215441590013','Banifox','https://www.banifox.com/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('barquilandia','j7pMcfZMCSBU',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('barquilandia','210791380017','Barquilandia','https://barquilandia.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('teksol','Hfnf8tztuL',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('teksol','214346030016','Teksol','https://www.teksol.com.uy/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('natura_trix','G264KWx3ta',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('natura_trix','220154580012','Natura Trix','https://www.naturatrix.com/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('virbac','3y3xDjC9bZ',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('virbac','210121720012','Virbac Uruguay','https://uy.virbac.com/',1);
        
        insert into USUARIOS(NOMBRE_USUARIO,CLAVE_ACCESO,ACTIVO) values('harrington','9nPp29Rz',1);
        insert into CLIENTES(NOMBRE_USUARIO,RUT,NOMBRE,SITIO_WEB,DISPONIBLE) values('harrington','210253650011','Harrington','https://www.harrington.com.uy/',1);

        -- POSTULANTES --

        -- USUARIOS-ROLES --

        INSERT INTO usuarios_roles(usuario_nombre_usuario, rol_nombre_rol)
        VALUES
            ('admin', 'consultor'),
            ('jacosta', 'consultor'),
            ('gcabrera', 'consultor'),
            ('wiriarte', 'consultor');

        insert into LOGS(FECHA_HORA,MENSAJE) values(now(),'Carga de datos iniciales');

    end if;
end^;

call CARGAR_DATOS_INICIALES()^;
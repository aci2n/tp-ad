-- borrar db si ya existe y crearla despues

use master 
if exists (select name from master.dbo.sysdatabases where name = 'TPAD')
begin
	alter database TPAD set single_user with rollback immediate
	drop database TPAD
end
create database TPAD

go

-- crear tablas

use TPAD

create table Productos(

	id_producto int identity not null,
	nombre varchar(30),
	tipo_fragilidad varchar(50),
	tipo_tratamiento varchar(50),
	profundidad float,
	ancho float,
	alto float,
	peso float,
	apilable int,
	manipulacion varchar(200),
	material varchar(50),
	consideraciones varchar(200),
	refrigerada bit,
	
	constraint pk_productos primary key (id_producto)
)

create table Clientes(
	
	id_cliente int identity not null,
	nombre varchar(100),
	
	constraint pk_clientes primary key (id_cliente)
)

create table Clientes_Empresas(
	
	id_cliente int not null,
	regular bit,
	deposito_previo bit,
	monto_autorizado float,
	monto_actual float,

	constraint pk_clientes_empresas primary key (id_cliente),
	constraint fk_ce_clientes foreign key (id_cliente) references Clientes
)

create table Clientes_Particulares(

	id_cliente int not null,
	apellido varchar(50),
	dni varchar(20),

	constraint pk_clientes_particulares primary key (id_cliente),
	constraint fk_cp_clientes foreign key (id_cliente) references Clientes
)

create table Ubicaciones(

	id_ubicacion int identity not null,
	pais varchar(50),
	provincia varchar(50),
	ciudad varchar(50),
	calle varchar(50),
	altura varchar(50),
	piso varchar(50),
	departamento varchar(50),
	longitud float,
	latitud float,
	
	constraint pk_ubicaciones primary key (id_ubicacion)
)

create table Sucursales(

	id_sucursal int identity not null,
	id_ubicacion int,
	nombre varchar(50),
	
	constraint pk_sucursales primary key (id_sucursal)
)

create table Empleados(

	id_empleado int identity not null,
	id_sucursal int,
	cuit varchar(50) not null,
	dni varchar(20),
	nombre varchar(50),
	apellido varchar(50),
	fecha_nacimiento datetime,
	puesto varchar(20),	

	constraint pk_empleados primary key(id_empleado),
	constraint fk_empleados_sucursales foreign key (id_sucursal) references Sucursales
)

create table Receptores(

	id_receptor int identity not null,
	id_cliente int,
	id_ubicacion int,
	dni varchar (20),
	nombre varchar(50),
	apellido varchar(50),
	
	
	constraint pk_receptores primary key(id_receptor),
	constraint fk_receptores_clientes foreign key(id_cliente) references Clientes,
	constraint fk_receptores_ubicaciones foreign key(id_ubicacion) references Ubicaciones
)

create table Proveedores(

	id_proveedor int identity not null, 
	cuit varchar(20) not null,
	nombre varchar(50),
	
	constraint pk_proveedores primary key(id_proveedor)
)

create table PlanesMantenimiento(

	id_plan_mantenimiento int identity not null	,
	kilometraje float,
	fecha_fabricacion datetime,
	punto_control float,
	intervalo_mantenimiento int,
	discriminador varchar(20),

	constraint PK_PlanesMantenimiento primary key (id_plan_mantenimiento)
	--constraint FK_PlanesMantenimiento_Vehiculos foreign key (id_vehiculo) references VehiculosLocales
)

create table Tareas(

	id_tarea int identity not null,
	id_plan_mantenimiento int,
	kilometraje float,
	fecha_entrega datetime,
	fecha_devolucion datetime,

	constraint PK_Tareas primary key (id_tarea),
	constraint FK_Tareas_PlanesMantenimiento foreign key (id_plan_mantenimiento) references PlanesMantenimiento
)	

create table Vehiculos(
	
	id_vehiculo int identity not null,
	patente varchar(10),
	peso float,
	tara float, 
	tarifa float, 
	ancho float,
	alto float,
	profundidad float,
 	tipo varchar(20),
	
	constraint pk_vehiculo primary key(id_vehiculo)
)

create table VehiculosExternos(
	
	id_vehiculo int not null,
	id_proveedor int,
	
	constraint pk_vehiculo_externo primary key(id_vehiculo),
	constraint fk_vehiculos_ext_proveedores foreign key(id_proveedor) references Proveedores
)

create table VehiculosLocales(

	id_vehiculo int not null,
	id_sucursal int,
	id_empleado int,
	id_plan_mantenimiento int,
	vencimiento_garantia datetime,
 
	constraint pk_vehiculos_locales primary key(id_vehiculo),
	constraint fk_vehiculos_sucursales foreign key(id_sucursal) references Sucursales,
	constraint fk_vehiculos_empleados foreign key(id_empleado) references Empleados,
	constraint fk_vehiculos_planes foreign key(id_plan_mantenimiento) references PlanesMantenimiento
)

create table Companias_Seguros(
	
	id_compania_seguros int identity not null,
	cuil varchar(20),
	nombre varchar(40),

	constraint pk_compania_seguros primary key (id_compania_seguros)
)

create table Seguros(
	
	id_seguro int identity not null,
	id_compania_seguros int,
	tipo_carga varchar(50),
	nombre varchar(40),
	tarifa float,
	
	constraint pk_seguros primary key (id_seguro),
	constraint fk_seguros_companiasseguros foreign key (id_compania_seguros) references Companias_Seguros,
	--constraint fk_seguros_tipo_carga foreign key (id_tipo_carga) references Tipos_Carga
)

create table Viajes(

	id_viaje int identity not null,
	id_seguro int,
	id_vehiculo int,
	id_sucursal int,
	id_origen int,
	id_destino int,
	fecha_salida datetime,
	fecha_llegada datetime,
	condicion_especial varchar(50),
	esta_atrasado bit,

	constraint pk_viajes primary key(id_viaje),
	constraint fk_viajes_origen foreign key(id_origen) references Ubicaciones,
	constraint fk_viajes_destino foreign key(id_destino) references Ubicaciones,
	constraint fk_viajes_vehiculos foreign key(id_vehiculo) references Vehiculos,
	constraint fk_viajes_seguros foreign key(id_seguro) references Seguros

)

create table Cargas(

	id_carga int identity not null,
	id_sucursal int,
	id_cliente int,
	id_ubicacionOrigen int,
	id_ubicacionDestino int,
	tipoCarga varchar(50),
	estadoCarga varchar(50),
	fechaMaximaEntrega datetime,
	fechaProbableEntrega datetime,
	manifiesto varchar(200),
	
	constraint pK_cargas primary key (id_carga),
	constraint fk_cargas_clientes foreign key (id_cliente) references Clientes,
	constraint fk_cargas_ubicacionesOrigen foreign key (id_ubicacionOrigen) references Ubicaciones,
	constraint fk_cargas_ubicacionesDestino foreign key (id_ubicacionDestino) references Ubicaciones,
	--constraint fk_cargas_viajes foreign key (id_viaje) references Viajes,
	constraint fk_cargas_sucursales foreign key (id_sucursal) references Sucursales
)

create table ItemsCarga(

	id_item_carga int identity not null,
	id_viaje int,
	id_carga int,
	fecha datetime,
	
	constraint pk_viaj_carg primary key (id_item_carga),
	constraint fk_viaj_viaje foreign key (id_viaje) references Viajes,
	constraint fk_viaj_carg foreign key (id_carga) references Cargas
)

create table Facturas(

	id_factura int identity not null,
	id_carga int,
	tipo_factura varchar(50),
	fecha_creacion datetime,
	monto_total float,

	constraint pk_facturas primary key(id_factura),
	constraint fk_items_cargas foreign key(id_carga) references Cargas
)

create table CobrosParciales (
	
	id_cobro_parcial int identity not null,
	id_factura int,
	monto float,
	fecha datetime,

	constraint pk_cobros_parcials primary key (id_cobro_parcial),
	constraint fk_cp_facturas foreign key (id_factura) references Facturas
)

create table Pagos(
	
	id_pago int identity not null,
	id_proveedor int,
	monto float,
	fecha datetime,
	pagado bit,

	constraint pk_pagos primary key(id_pago),
	constraint fk_pagos_proveedores foreign key(id_proveedor) references Proveedores
)

create table Distancia_Sucursales(

	id_distancia_sucursales int identity not null,
	id_sucursal_a int,
	id_sucursal_b int,
	distancia float,
	duracion float,
	costo float,
	
	constraint pk_dist_sucur primary key(id_distancia_sucursales),
	constraint fk_sucursal_a foreign key(id_sucursal_a) references Sucursales,
	constraint fk_sucursal_b foreign key(id_sucursal_b) references Sucursales,
)

create table ParadasIntermedias (

	id_parada_intermedia int identity not null,
	id_viaje int,	
	id_ubicacion int,
	llegada date,
	checked bit,

	constraint pk_paradasInter primary key(id_parada_intermedia),
	constraint fk_paradas_viajes foreign key(id_viaje) references Viajes,
	constraint fk_paradas_ubicacion foreign key(id_ubicacion) references Ubicaciones
)

create table Cargas_Productos(

	id_carga_producto int identity not null,
	id_carga int,
	id_producto int,
	cantidad float,
	
	constraint pk_cargas_productos primary key (id_carga_producto),
	constraint fk_productos foreign key (id_producto) references Productos,
	constraint fk_cargas foreign key(id_carga) references Cargas
)

create table Productos_CondicionesEspeciales (
	id_producto int,
	condicion_especial varchar(50),
	
	constraint pk_pce primary key (id_producto, condicion_especial),
	constraint fk_pce_productos foreign key (id_producto) references Productos
)	

create table Empresas_Productos (
	id_empresa int,
	id_producto int,

	constraint pk_ep primary key (id_empresa, id_producto),
	constraint fk_ep_empresas foreign key (id_empresa) references Clientes_Empresas,
	constraint fk_ep_productos foreign key (id_producto) references Productos
)

create table Viajes_CondicionesEspeciales (
	id_viaje int,
	condicion_especial varchar(50),

	constraint pk_viajes_ce primary key (id_viaje, condicion_especial),
	constraint fk_viajece_viajes foreign key (id_viaje) references Viajes
)

-- crear usuario

if exists (select name from master.sys.server_principals where name = 'ADuser')
begin
	drop login ADuser
end
create login ADuser with password = 'ADpassword', check_policy = off
create user ADuser for login ADuser
exec sp_addrolemember 'db_owner', 'ADuser'

go

-- sp auxiliares

create procedure tpad_seleccionartodo as
begin
	declare @nombreTabla varchar(200)
	declare cursorTablas cursor fast_forward
	for select name as nombreTabla from tpad.sys.objects where type = 'U'
	open cursorTablas
	fetch next from cursorTablas into @nombreTabla
	while (@@FETCH_STATUS <> -1)
	begin
		execute ('select ''' + @nombreTabla + ''' as nombre_tabla, * from ' +  @nombretabla)
		fetch next from cursorTablas into @nombretabla
	end
	close cursorTablas
	deallocate cursorTablas
end

go
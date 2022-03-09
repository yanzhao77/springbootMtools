-- public."MenuInfo" definition

-- Drop table

-- DROP TABLE public."MenuInfo";

CREATE TABLE public."MenuInfo" (
                                   id serial4 NOT NULL,
                                   "menuName" varchar NOT NULL,
                                   CONSTRAINT menuinfo_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX menuinfo_id_uindex ON public."MenuInfo" USING btree (id);


-- public."RoleInfo" definition

-- Drop table

-- DROP TABLE public."RoleInfo";

CREATE TABLE public."RoleInfo" (
                                   id serial4 NOT NULL,
                                   "roleName" varchar NOT NULL,
                                   CONSTRAINT roleinfo_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX roleinfo_id_uindex ON public."RoleInfo" USING btree (id);


-- public."RoleMenu" definition

-- Drop table

-- DROP TABLE public."RoleMenu";

CREATE TABLE public."RoleMenu" (
                                   id serial4 NOT NULL,
                                   "RoleId" int4 NOT NULL,
                                   "MenuId" int4 NOT NULL,
                                   CONSTRAINT rolemenu_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX rolemenu_id_uindex ON public."RoleMenu" USING btree (id);


-- public."UserRole" definition

-- Drop table

-- DROP TABLE public."UserRole";

CREATE TABLE public."UserRole" (
                                   id serial4 NOT NULL,
                                   "UserId" int4 NOT NULL,
                                   "RoleId" int4 NOT NULL,
                                   CONSTRAINT userrole_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX userrole_id_uindex ON public."UserRole" USING btree (id);


-- public.message definition

-- Drop table

-- DROP TABLE public.message;

CREATE TABLE public.message (
                                mq_name varchar(20) NULL,
                                msg_data varchar(200) NULL,
                                msg_producer int8 NULL,
                                msg_consumer int8 NULL,
                                msg_level int4 NULL,
                                mq_level int4 NULL,
                                status int4 NULL DEFAULT 1,
                                createtime timestamp NOT NULL DEFAULT now(),
                                id varchar NULL,
                                msg_type int4 NULL
);


-- public.qtemp definition

-- Drop table

-- DROP TABLE public.qtemp;

CREATE TABLE public.qtemp (
                              id int4 NOT NULL DEFAULT nextval('qtemp_qid_seq'::regclass),
                              uid int4 NOT NULL,
                              context varchar NOT NULL,
                              insertsysdate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              upduserid int4 NULL,
                              updsysdate timestamp NULL
);
CREATE UNIQUE INDEX qtemp_id_uindex ON public.qtemp USING btree (id);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
                              id int4 NOT NULL DEFAULT nextval('user_id_seq'::regclass),
                              username varchar(20) NOT NULL,
                              pwd varchar(100) NULL,
                              remake varchar(50) NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT user_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX user_id_uindex ON public.users USING btree (id);
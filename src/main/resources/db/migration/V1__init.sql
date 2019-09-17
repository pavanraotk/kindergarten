create SCHEMA IF NOT EXISTS kindergarten;

create sequence IF NOT EXISTS kindergarten.teacher_id_seq
    START with 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create TABLE IF NOT EXISTS kindergarten.teacher (
  id                bigint                          NOT NULL DEFAULT nextval('kindergarten.teacher_id_seq'::regclass),
  first_name        varchar(255),
  last_name         varchar(255),
  email_id          varchar(255),
  mobile_number     bigint,
  address           varchar(1024),
  qualifications    varchar(255),
  active            boolean,
  created           timestamp without time zone,
  modified          timestamp without time zone,
  CONSTRAINT teacher_pkey PRIMARY KEY (id),
  CONSTRAINT teacher_ukey UNIQUE (email_id)
);
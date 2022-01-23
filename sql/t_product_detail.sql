-- Table: public.product_detail

-- DROP TABLE IF EXISTS public.product_detail;

CREATE TABLE IF NOT EXISTS public.product_detail
(
    id bigint NOT NULL DEFAULT nextval('product_detail_id_seq'::regclass),
    name character varying(1024) COLLATE pg_catalog."default",
    price character varying(1024) COLLATE pg_catalog."default",
    source character varying(1024) COLLATE pg_catalog."default",
    image_url character varying(1024) COLLATE pg_catalog."default",
    product_url character varying(1024) COLLATE pg_catalog."default",
    user_input character varying(1024) COLLATE pg_catalog."default"
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product_detail
    OWNER to postgres;
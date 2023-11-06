--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    id bigint NOT NULL,
    username character varying(100) NOT NULL,
    message text NOT NULL,
    "time" timestamp without time zone NOT NULL
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.messages_id_seq OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messages_id_seq OWNED BY public.messages.id;


--
-- Name: salts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salts (
    username character varying(100) NOT NULL,
    salt bytea NOT NULL
);


ALTER TABLE public.salts OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(100) NOT NULL,
    password character varying(400) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: messages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages ALTER COLUMN id SET DEFAULT nextval('public.messages_id_seq'::regclass);


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (id, username, message, "time") FROM stdin;
1	belrol	Hello	2023-11-06 10:44:09
2	belrol	what up	55817-08-18 13:45:50.000128
3	belrol	you good?	55817-08-20 13:42:08.999936
4	br	me good	55817-08-21 07:42:40.999936
5	belrol	that's good	55817-08-23 04:53:10.000128
6	br	oh how cool	55817-08-23 06:27:37.999872
7	br	you good too?	55817-08-26 05:19:12.999936
8	belrol	sure	55817-08-26 06:24:23.000064
\.


--
-- Data for Name: salts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.salts (username, salt) FROM stdin;
belrol	\\xb75be37e12540510b5918a81fa154e7b
br	\\x56f1edc0e39a621384c12bdfb17065de
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (username, password) FROM stdin;
rr	9620
belrol	��W�{�^mP���f\n\bX+�AО�D���\\��
br	�\fΆ&l��۸�����]��<Z3PQ�On
\.


--
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messages_id_seq', 8, true);


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: salts salts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salts
    ADD CONSTRAINT salts_pkey PRIMARY KEY (username);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- Name: messages messages_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_username_fkey FOREIGN KEY (username) REFERENCES public.users(username);


--
-- Name: salts salts_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salts
    ADD CONSTRAINT salts_username_fkey FOREIGN KEY (username) REFERENCES public.users(username);


--
-- PostgreSQL database dump complete
--


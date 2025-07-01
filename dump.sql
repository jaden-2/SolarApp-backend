--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: batteries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.batteries (
    id integer NOT NULL,
    brand character varying(255) NOT NULL,
    current_capacity integer NOT NULL,
    energy_capacity double precision NOT NULL,
    type character varying(255) NOT NULL,
    charge_cycle integer NOT NULL,
    model character varying(255),
    voltage double precision NOT NULL
);


ALTER TABLE public.batteries OWNER TO postgres;

--
-- Name: batteries_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.batteries_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.batteries_id_seq OWNER TO postgres;

--
-- Name: batteries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.batteries_id_seq OWNED BY public.batteries.id;


--
-- Name: breakers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.breakers (
    id integer NOT NULL,
    model character varying(255) NOT NULL,
    current integer NOT NULL,
    max_voltage integer NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.breakers OWNER TO postgres;

--
-- Name: breakers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.breakers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.breakers_id_seq OWNER TO postgres;

--
-- Name: breakers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.breakers_id_seq OWNED BY public.breakers.id;


--
-- Name: controllers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.controllers (
    id integer NOT NULL,
    brand character varying(255) NOT NULL,
    model character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    max_charge_current integer NOT NULL,
    min_voltage integer NOT NULL,
    max_voltage integer NOT NULL,
    max_power integer NOT NULL,
    system_voltage integer NOT NULL
);


ALTER TABLE public.controllers OWNER TO postgres;

--
-- Name: controllers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.controllers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.controllers_id_seq OWNER TO postgres;

--
-- Name: controllers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.controllers_id_seq OWNED BY public.controllers.id;


--
-- Name: inverters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inverters (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    model character varying(255) NOT NULL,
    capacity double precision NOT NULL,
    system_voltage integer NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.inverters OWNER TO postgres;

--
-- Name: inverters_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inverters_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inverters_id_seq OWNER TO postgres;

--
-- Name: inverters_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inverters_id_seq OWNED BY public.inverters.id;


--
-- Name: panels; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.panels (
    id integer NOT NULL,
    brand character varying(255) NOT NULL,
    model character varying(255) NOT NULL,
    power integer NOT NULL,
    vmp double precision NOT NULL,
    imp double precision NOT NULL,
    voc double precision NOT NULL,
    isc double precision NOT NULL,
    lenght_m double precision NOT NULL,
    width_m double precision NOT NULL,
    type character varying(255) NOT NULL,
    design character varying(255)
);


ALTER TABLE public.panels OWNER TO postgres;

--
-- Name: panels_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.panels_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.panels_id_seq OWNER TO postgres;

--
-- Name: panels_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.panels_id_seq OWNED BY public.panels.id;


--
-- Name: batteries id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batteries ALTER COLUMN id SET DEFAULT nextval('public.batteries_id_seq'::regclass);


--
-- Name: breakers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.breakers ALTER COLUMN id SET DEFAULT nextval('public.breakers_id_seq'::regclass);


--
-- Name: controllers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.controllers ALTER COLUMN id SET DEFAULT nextval('public.controllers_id_seq'::regclass);


--
-- Name: inverters id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inverters ALTER COLUMN id SET DEFAULT nextval('public.inverters_id_seq'::regclass);


--
-- Name: panels id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.panels ALTER COLUMN id SET DEFAULT nextval('public.panels_id_seq'::regclass);


--
-- Data for Name: batteries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.batteries (id, brand, current_capacity, energy_capacity, type, charge_cycle, model, voltage) FROM stdin;
9	Narada	100	1.2	AGM	800	12NP100	12
14	Zumax or oem	200	2.4	AGM	3000	\N	12
15	Zumax or oem	100	1.2	AGM	3000	\N	12
16	Zumax or oem	80	0.96	AGM	3000	\N	12
17	Zumax or oem	50	0.6	AGM	3000	\N	12
18	Zumax or oem	250	3	AGM	3000	\N	12
21	Cworth energy	100	1.28	LITHIUM	3000	CE-GCL-12100	12
22	Cworth energy	200	2.56	LITHIUM	3000	CE-GCL-12200	12
8	Trojan	225	1.35	FLOODED	1200	T-105	6
12	DÃ©cor	220	2.64	FLOODED	1800	TT2200	12
7	Renogy	100	1.2	LITHIUM	4000	RBT100LFP12-BT	12
10	Teze	300	15.36	LITHIUM	6000	\N	48
11	ESS	300	15.36	LITHIUM	11000	AMB-JCBG	48
13	Delongtop	200	10.24	LITHIUM	6000	HS51200-10	48
23	Buaman	400	20.48	LITHIUM	6000	BUAMAN	48
\.


--
-- Data for Name: breakers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.breakers (id, model, current, max_voltage, type) FROM stdin;
2	Schneider A9F74232	32	240	AC
3	Siemens 5SY6220-7	20	250	AC
4	EBASEE	30	240	AC
5	EBASEE	40	240	AC
6	EBASEE	50	240	AC
7	EBASEE	60	240	AC
8	OM1-250	40	400	DC
9	OM1-251	60	400	DC
10	OM1-252	80	400	DC
11	OM1-253	100	400	DC
12	OM1-254	120	400	DC
13	OM1-255	140	400	DC
14	OM1-256	160	400	DC
15	OM1-257	180	400	DC
16	OM1-258	200	400	DC
17	OM1-259	220	400	DC
18	OM1-260	240	400	DC
19	OM1-261	250	400	DC
20	OM1-259	220	750	DC
21	OM1-260	240	500	DC
22	OM1-261	250	120	DC
23	OM1-262	250	120	DC
1	ABB S203-DC25	25	500	DC
\.


--
-- Data for Name: controllers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.controllers (id, brand, model, type, max_charge_current, min_voltage, max_voltage, max_power, system_voltage) FROM stdin;
1	Lumiax	MT1050-EU	MPPT	10	45	130	12	14
2	Lumiax	MT1550-EU	MPPT	15	35	200	12	14
3	Lumiax	MT2010	MPPT	20	100	520	24	14
4	Lumiax	MT2010	MPPT	20	100	260	12	14
5	Lumiax	MT3010	MPPT	30	100	780	24	14
6	Lumiax	MT3011	MPPT	30	100	390	12	14
7	Lumiax	MT6020-Pro	MPPT	60	190	750	12	14
8	Lumiax	MT6020-Pro	MPPT	60	190	1500	24	14
9	Lumiax	MT6020-Pro	MPPT	60	190	3000	48	14
10	Felicity	SCCM12048-II	MPPT	120	170	1650	12	15
11	Felicity	SCCM12048-II	MPPT	120	170	3300	24	30
12	Felicity	SCCM12048-II	MPPT	120	170	6600	48	60
13	Y&H	SY8048	MPPT	80	24	2880	12	15
14	Y&H	SY8048	MPPT	80	55	2880	24	30
15	Y&H	SY8048	MPPT	80	69	2880	36	45
16	Y&H	SY8048	MPPT	80	90	2880	48	60
\.


--
-- Data for Name: inverters; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inverters (id, name, model, capacity, system_voltage, type) FROM stdin;
1	Fenglinhuoshan	TPC-1000W	1.25	12	Sine
2	ADC Power	ADC-XP500	0.625	12	Sine
3	byx	AW-56	3.6	24	Sine
4	byx	AW-56	3.6	48	Sine
5	byx	AW-56	5	24	Sine
6	byx	AW-56	5	48	Sine
7	WTHD	WTHD-LI-1K	1	12	Sine
8	WTHD	WTHD-LI-1.5K	1.5	12	Sine
9	WTHD	WTHD-LI-2K	2.5	24	Sine
10	WTHD	WTHD-LI-3K	3	24	Sine
11	WTHD	WTHD-LI-5K	5	48	Sine
12	WTHD	WTHD-LI-10K	10	48	Sine
13	WTHD	WTHD-LI-12K	12	48	Sine
\.


--
-- Data for Name: panels; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.panels (id, brand, model, power, vmp, imp, voc, isc, lenght_m, width_m, type, design) FROM stdin;
1	Canadian Solar	CS6R-400MS	400	34.1	11.73	40.5	12.5	1.722	1.134	Monofacial	MONO
2	Longi	LR5-54HPH-405M	405	31.3	12.94	37.4	13.63	1.722	1.134	Monofacial	MONO
3	Jinko	JKM410M-54HL4-V	410	31.4	13.1	37.6	13.89	1.722	1.134	Monofacial	MONO
4	Canadian Solar	CS6.2-66TB-595	450	38	11.84	44.9	12.73	2.382	1.134	Monofacial	MONO
5	Canadian Solar	CS6.2-66TB	595	40.2	14.81	47.4	15.79	2.382	1.134	Bifacial	MONO
6	Canadian Solar	CS6.2-66TB	600	40.4	14.86	47.6	15.85	2.382	1.134	Bifacial	MONO
7	Canadian Solar	CS6.2-66TB	625	40.2	15.55	47.4	16.58	2.382	1.134	Bifacial	MONO
8	Canadian Solar	CS6.2-66TB	630	40.4	15.6	47.6	16.64	2.382	1.134	Bifacial	MONO
9	Canadian Solar	CS6.2-66TB	655	40.2	16.29	47.4	17.37	2.382	1.134	Bifacial	MONO
10	Canadian Solar	CS6.2-66TB	660	40.4	16.35	47.6	17.44	2.382	1.134	Bifacial	MONO
11	Canadian Solar	CS6.2-66TB	714	40.2	17.77	47.4	18.95	2.382	1.134	Bifacial	MONO
12	Canadian Solar	CS6.2-66TB	720	40.4	17.83	47.6	19.02	2.382	1.134	Bifacial	MONO
\.


--
-- Name: batteries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.batteries_id_seq', 23, true);


--
-- Name: breakers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.breakers_id_seq', 23, true);


--
-- Name: controllers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.controllers_id_seq', 16, true);


--
-- Name: inverters_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inverters_id_seq', 13, true);


--
-- Name: panels_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.panels_id_seq', 12, true);


--
-- Name: batteries batteries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.batteries
    ADD CONSTRAINT batteries_pkey PRIMARY KEY (id);


--
-- Name: breakers breakers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.breakers
    ADD CONSTRAINT breakers_pkey PRIMARY KEY (id);


--
-- Name: controllers controllers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.controllers
    ADD CONSTRAINT controllers_pkey PRIMARY KEY (id);


--
-- Name: inverters inverters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inverters
    ADD CONSTRAINT inverters_pkey PRIMARY KEY (id);


--
-- Name: panels panels_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.panels
    ADD CONSTRAINT panels_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


--
-- PostgreSQL database dump complete
--


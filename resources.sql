--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-07-01 13:28:08

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 16461)
-- Name: batteries; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.batteries (
    id integer NOT NULL,
    brand character varying(255) NOT NULL,
    current_capacity integer NOT NULL,
    energy_capacity double precision NOT NULL,
    type character varying(255) NOT NULL,
    charge_cycle integer NOT NULL,
    model character varying(255),
    voltage double precision NOT NULL
);


ALTER TABLE solar_inventory.batteries OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16460)
-- Name: batteries_id_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.batteries_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.batteries_id_seq OWNER TO postgres;

--
-- TOC entry 4969 (class 0 OID 0)
-- Dependencies: 225
-- Name: batteries_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.batteries_id_seq OWNED BY solar_inventory.batteries.id;


--
-- TOC entry 224 (class 1259 OID 16452)
-- Name: breakers; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.breakers (
    id integer NOT NULL,
    model character varying(255) NOT NULL,
    current integer NOT NULL,
    max_voltage integer NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE solar_inventory.breakers OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16451)
-- Name: breakers_id_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.breakers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.breakers_id_seq OWNER TO postgres;

--
-- TOC entry 4970 (class 0 OID 0)
-- Dependencies: 223
-- Name: breakers_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.breakers_id_seq OWNED BY solar_inventory.breakers.id;


--
-- TOC entry 222 (class 1259 OID 16443)
-- Name: controllers; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.controllers (
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


ALTER TABLE solar_inventory.controllers OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16442)
-- Name: controllers_id_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.controllers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.controllers_id_seq OWNER TO postgres;

--
-- TOC entry 4971 (class 0 OID 0)
-- Dependencies: 221
-- Name: controllers_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.controllers_id_seq OWNED BY solar_inventory.controllers.id;


--
-- TOC entry 218 (class 1259 OID 16425)
-- Name: inverters; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.inverters (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    model character varying(255) NOT NULL,
    capacity double precision NOT NULL,
    system_voltage integer NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE solar_inventory.inverters OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16424)
-- Name: inverters_id_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.inverters_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.inverters_id_seq OWNER TO postgres;

--
-- TOC entry 4972 (class 0 OID 0)
-- Dependencies: 217
-- Name: inverters_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.inverters_id_seq OWNED BY solar_inventory.inverters.id;


--
-- TOC entry 220 (class 1259 OID 16434)
-- Name: panels; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.panels (
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


ALTER TABLE solar_inventory.panels OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16433)
-- Name: panels_id_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.panels_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.panels_id_seq OWNER TO postgres;

--
-- TOC entry 4973 (class 0 OID 0)
-- Dependencies: 219
-- Name: panels_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.panels_id_seq OWNED BY solar_inventory.panels.id;


--
-- TOC entry 4798 (class 2604 OID 16464)
-- Name: batteries id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.batteries ALTER COLUMN id SET DEFAULT nextval('solar_inventory.batteries_id_seq'::regclass);


--
-- TOC entry 4797 (class 2604 OID 16455)
-- Name: breakers id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.breakers ALTER COLUMN id SET DEFAULT nextval('solar_inventory.breakers_id_seq'::regclass);


--
-- TOC entry 4796 (class 2604 OID 16446)
-- Name: controllers id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.controllers ALTER COLUMN id SET DEFAULT nextval('solar_inventory.controllers_id_seq'::regclass);


--
-- TOC entry 4794 (class 2604 OID 16428)
-- Name: inverters id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.inverters ALTER COLUMN id SET DEFAULT nextval('solar_inventory.inverters_id_seq'::regclass);


--
-- TOC entry 4795 (class 2604 OID 16437)
-- Name: panels id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.panels ALTER COLUMN id SET DEFAULT nextval('solar_inventory.panels_id_seq'::regclass);


--
-- TOC entry 4963 (class 0 OID 16461)
-- Dependencies: 226
-- Data for Name: batteries; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.batteries (id, brand, current_capacity, energy_capacity, type, charge_cycle, model, voltage) FROM stdin;
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
-- TOC entry 4961 (class 0 OID 16452)
-- Dependencies: 224
-- Data for Name: breakers; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.breakers (id, model, current, max_voltage, type) FROM stdin;
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
-- TOC entry 4959 (class 0 OID 16443)
-- Dependencies: 222
-- Data for Name: controllers; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.controllers (id, brand, model, type, max_charge_current, min_voltage, max_voltage, max_power, system_voltage) FROM stdin;
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
-- TOC entry 4955 (class 0 OID 16425)
-- Dependencies: 218
-- Data for Name: inverters; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.inverters (id, name, model, capacity, system_voltage, type) FROM stdin;
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
-- TOC entry 4957 (class 0 OID 16434)
-- Dependencies: 220
-- Data for Name: panels; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.panels (id, brand, model, power, vmp, imp, voc, isc, lenght_m, width_m, type, design) FROM stdin;
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
-- TOC entry 4974 (class 0 OID 0)
-- Dependencies: 225
-- Name: batteries_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.batteries_id_seq', 23, true);


--
-- TOC entry 4975 (class 0 OID 0)
-- Dependencies: 223
-- Name: breakers_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.breakers_id_seq', 23, true);


--
-- TOC entry 4976 (class 0 OID 0)
-- Dependencies: 221
-- Name: controllers_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.controllers_id_seq', 16, true);


--
-- TOC entry 4977 (class 0 OID 0)
-- Dependencies: 217
-- Name: inverters_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.inverters_id_seq', 13, true);


--
-- TOC entry 4978 (class 0 OID 0)
-- Dependencies: 219
-- Name: panels_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.panels_id_seq', 12, true);


--
-- TOC entry 4808 (class 2606 OID 16468)
-- Name: batteries batteries_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.batteries
    ADD CONSTRAINT batteries_pkey PRIMARY KEY (id);


--
-- TOC entry 4806 (class 2606 OID 16459)
-- Name: breakers breakers_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.breakers
    ADD CONSTRAINT breakers_pkey PRIMARY KEY (id);


--
-- TOC entry 4804 (class 2606 OID 16450)
-- Name: controllers controllers_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.controllers
    ADD CONSTRAINT controllers_pkey PRIMARY KEY (id);


--
-- TOC entry 4800 (class 2606 OID 16432)
-- Name: inverters inverters_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.inverters
    ADD CONSTRAINT inverters_pkey PRIMARY KEY (id);


--
-- TOC entry 4802 (class 2606 OID 16441)
-- Name: panels panels_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.panels
    ADD CONSTRAINT panels_pkey PRIMARY KEY (id);


-- Completed on 2025-07-01 13:28:09

--
-- PostgreSQL database dump complete
--


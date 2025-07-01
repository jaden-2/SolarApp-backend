--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-07-01 10:31:42

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
-- TOC entry 5 (class 2615 OID 16423)
-- Name: solar_inventory; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA solar_inventory;


ALTER SCHEMA solar_inventory OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 235 (class 1259 OID 41030)
-- Name: array_specs; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.array_specs (
    array_id integer NOT NULL,
    brand character varying(255),
    calculate_panel_capacity double precision,
    parallel integer,
    series integer,
    power_w integer,
    vmax double precision,
    type smallint,
    imp double precision,
    isc double precision,
    voc double precision,
    length_m double precision,
    width_m double precision,
    model character varying(255),
    swg character varying(255),
    diameter_mm double precision,
    max_current double precision,
    creator character varying(255),
    CONSTRAINT array_specs_type_check CHECK (((type >= 0) AND (type <= 1)))
);


ALTER TABLE solar_inventory.array_specs OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 33039)
-- Name: array_specs_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.array_specs_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.array_specs_seq OWNER TO postgres;

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
-- TOC entry 5052 (class 0 OID 0)
-- Dependencies: 225
-- Name: batteries_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.batteries_id_seq OWNED BY solar_inventory.batteries.id;


--
-- TOC entry 236 (class 1259 OID 41038)
-- Name: battery_specs; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.battery_specs (
    battery_id integer NOT NULL,
    battery_current_capacity_ah integer,
    battery_energy_capacity_ah double precision NOT NULL,
    battery_type smallint,
    battery_voltage double precision NOT NULL,
    brand character varying(255),
    parallel integer,
    series integer,
    required_bank_capacity_ah double precision NOT NULL,
    creator character varying(255),
    CONSTRAINT battery_specs_battery_type_check CHECK (((battery_type >= 0) AND (battery_type <= 3)))
);


ALTER TABLE solar_inventory.battery_specs OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 33040)
-- Name: battery_specs_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.battery_specs_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.battery_specs_seq OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 41046)
-- Name: breaker_specs; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.breaker_specs (
    breaker_id integer NOT NULL,
    calculated_capacity double precision,
    current integer,
    maximum_voltage integer,
    model character varying(255),
    type character varying(255),
    creator character varying(255)
);


ALTER TABLE solar_inventory.breaker_specs OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 33041)
-- Name: breaker_specs_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.breaker_specs_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.breaker_specs_seq OWNER TO postgres;

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
-- TOC entry 5053 (class 0 OID 0)
-- Dependencies: 223
-- Name: breakers_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.breakers_id_seq OWNED BY solar_inventory.breakers.id;


--
-- TOC entry 238 (class 1259 OID 41053)
-- Name: controller_specs; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.controller_specs (
    controller_id integer NOT NULL,
    brand character varying(255),
    calculated_capacity double precision NOT NULL,
    parallel integer,
    series integer,
    maximum_charge_current integer NOT NULL,
    maximum_volts integer NOT NULL,
    minimum_volts integer NOT NULL,
    model character varying(255),
    type character varying(255),
    creator character varying(255)
);


ALTER TABLE solar_inventory.controller_specs OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 33042)
-- Name: controller_specs_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.controller_specs_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.controller_specs_seq OWNER TO postgres;

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
-- TOC entry 5054 (class 0 OID 0)
-- Dependencies: 221
-- Name: controllers_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.controllers_id_seq OWNED BY solar_inventory.controllers.id;


--
-- TOC entry 243 (class 1259 OID 65536)
-- Name: creator_token; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.creator_token (
    token_id character varying(255) NOT NULL,
    created_at timestamp(6) with time zone,
    expires_at timestamp(6) with time zone,
    is_revoked boolean,
    is_used boolean,
    creator_username character varying(255) NOT NULL
);


ALTER TABLE solar_inventory.creator_token OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 41060)
-- Name: inverter_specs; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.inverter_specs (
    inverter_id integer NOT NULL,
    calculated_inverter_capacity_kva double precision,
    capacity_kva double precision NOT NULL,
    parallel integer,
    series integer,
    model character varying(255),
    name character varying(255),
    system_voltage integer,
    type character varying(255),
    creator character varying(255)
);


ALTER TABLE solar_inventory.inverter_specs OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 33043)
-- Name: inverter_specs_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.inverter_specs_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.inverter_specs_seq OWNER TO postgres;

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
-- TOC entry 5055 (class 0 OID 0)
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
-- TOC entry 5056 (class 0 OID 0)
-- Dependencies: 219
-- Name: panels_id_seq; Type: SEQUENCE OWNED BY; Schema: solar_inventory; Owner: postgres
--

ALTER SEQUENCE solar_inventory.panels_id_seq OWNED BY solar_inventory.panels.id;


--
-- TOC entry 240 (class 1259 OID 49152)
-- Name: reports; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.reports (
    id integer NOT NULL,
    created_at timestamp(6) with time zone,
    report jsonb,
    username character varying(255) NOT NULL,
    system_report_id integer NOT NULL
);


ALTER TABLE solar_inventory.reports OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 33044)
-- Name: reports_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.reports_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.reports_seq OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 49178)
-- Name: request; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.request (
    array_series_length integer NOT NULL,
    battery_type character varying(255) NOT NULL,
    days_of_backup double precision NOT NULL,
    energy_wh double precision NOT NULL,
    load_w double precision NOT NULL,
    psh double precision NOT NULL,
    brand character varying(255) NOT NULL,
    power integer NOT NULL,
    system_volts integer NOT NULL,
    title character varying(255)
);


ALTER TABLE solar_inventory.request OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 49159)
-- Name: system_report; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.system_report (
    report_id integer NOT NULL,
    created_at timestamp(6) with time zone,
    update_at timestamp(6) with time zone,
    wire_details jsonb,
    battery_bank integer,
    controller integer,
    creator character varying(255) NOT NULL,
    dc_breaker integer,
    inverter integer,
    array_series integer NOT NULL,
    battery_type character varying(255) NOT NULL,
    days_of_backup double precision NOT NULL,
    energy_wh double precision NOT NULL,
    load_w double precision NOT NULL,
    psh double precision NOT NULL,
    panel_brand character varying(255) NOT NULL,
    panel_power integer NOT NULL,
    sys_volts integer NOT NULL,
    solar_array integer,
    title character varying(255)
);


ALTER TABLE solar_inventory.system_report OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 33045)
-- Name: system_report_seq; Type: SEQUENCE; Schema: solar_inventory; Owner: postgres
--

CREATE SEQUENCE solar_inventory.system_report_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE solar_inventory.system_report_seq OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24576)
-- Name: users; Type: TABLE; Schema: solar_inventory; Owner: postgres
--

CREATE TABLE solar_inventory.users (
    username character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    password character varying(255)
);


ALTER TABLE solar_inventory.users OWNER TO postgres;

--
-- TOC entry 4813 (class 2604 OID 16464)
-- Name: batteries id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.batteries ALTER COLUMN id SET DEFAULT nextval('solar_inventory.batteries_id_seq'::regclass);


--
-- TOC entry 4812 (class 2604 OID 16455)
-- Name: breakers id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.breakers ALTER COLUMN id SET DEFAULT nextval('solar_inventory.breakers_id_seq'::regclass);


--
-- TOC entry 4811 (class 2604 OID 16446)
-- Name: controllers id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.controllers ALTER COLUMN id SET DEFAULT nextval('solar_inventory.controllers_id_seq'::regclass);


--
-- TOC entry 4809 (class 2604 OID 16428)
-- Name: inverters id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.inverters ALTER COLUMN id SET DEFAULT nextval('solar_inventory.inverters_id_seq'::regclass);


--
-- TOC entry 4810 (class 2604 OID 16437)
-- Name: panels id; Type: DEFAULT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.panels ALTER COLUMN id SET DEFAULT nextval('solar_inventory.panels_id_seq'::regclass);


--
-- TOC entry 5038 (class 0 OID 41030)
-- Dependencies: 235
-- Data for Name: array_specs; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.array_specs (array_id, brand, calculate_panel_capacity, parallel, series, power_w, vmax, type, imp, isc, voc, length_m, width_m, model, swg, diameter_mm, max_current, creator) FROM stdin;
802	Canadian Solar	2153.846153846154	3	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG6	4.877	44.2	Jaden
952	Canadian Solar	2461.5384615384614	4	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG5	5.385	52.3	Jaden
955	Canadian Solar	2153.846153846154	3	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG6	4.877	44.2	Jaden
956	Canadian Solar	2153.846153846154	3	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG6	4.877	44.2	Jaden
957	Canadian Solar	3076.923076923077	4	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG5	5.385	52.3	Jaden
959	Canadian Solar	2769.230769230769	4	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG5	5.385	52.3	Jaden
1003	Canadian Solar	2769.230769230769	4	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG5	5.385	52.3	Jaden
1202	Canadian Solar	0	0	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG9	3.658	21.2	Jaden
1203	Canadian Solar	1025.6410256410256	2	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG8	4.064	26.5	Jaden
1252	Canadian Solar	1282.051282051282	2	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG8	4.064	26.5	Jaden
1258	Canadian Solar	923.0769230769231	2	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG8	4.064	26.5	Jaden
1265	Canadian Solar	769.2307692307692	1	3	714	40.2	1	17.77	18.95	47.4	2.382	1.134	CS6.2-66TB	SWG9	3.658	21.2	Jaden
1266	Canadian Solar	2794.871794871795	3	2	630	40.4	1	15.6	16.64	47.6	2.382	1.134	CS6.2-66TB	SWG5	5.385	52.3	Jaden
1302	Canadian Solar	25641.02564102564	12	3	720	40.4	1	17.83	19.02	47.6	2.382	1.134	CS6.2-66TB	SWG5_0	10.973	227	Jaden
1303	Canadian Solar	2769.230769230769	4	2	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG5	5.385	52.3	Jaden
1353	Canadian Solar	2769.230769230769	3	3	400	34.1	1	11.73	12.5	40.5	1.722	1.134	CS6R-400MS	SWG6	4.877	44.2	Jaden
1402	Canadian Solar	2564.102564102564	2	3	450	38	1	11.84	12.73	44.9	2.382	1.134	CS6.2-66TB-595	SWG8	4.064	26.5	Jaden
1452	Canadian Solar	923.0769230769231	1	3	630	40.4	1	15.6	16.64	47.6	2.382	1.134	CS6.2-66TB	SWG9	3.658	21.2	Jaden
\.


--
-- TOC entry 5029 (class 0 OID 16461)
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
-- TOC entry 5039 (class 0 OID 41038)
-- Dependencies: 236
-- Data for Name: battery_specs; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.battery_specs (battery_id, battery_current_capacity_ah, battery_energy_capacity_ah, battery_type, battery_voltage, brand, parallel, series, required_bank_capacity_ah, creator) FROM stdin;
852	250	3	2	12	Zumax or oem	2	4	254.62962962962965	Jaden
1002	250	3	2	12	Zumax or oem	2	4	291.005291005291	Jaden
1005	200	2.56	3	12	Cworth energy	2	4	209.6949891067538	Jaden
1006	200	2.56	3	12	Cworth energy	2	4	209.6949891067538	Jaden
1007	300	15.36	3	48	Teze	1	1	299.56427015250546	Jaden
1009	300	15.36	3	48	Teze	1	1	269.6078431372549	Jaden
1053	300	15.36	3	48	Teze	1	1	269.6078431372549	Jaden
1252	100	1.2	3	12	Renogy	0	4	0	Jaden
1253	100	1.2	3	12	Renogy	2	4	119.82570806100217	Jaden
1302	225	1.35	0	6	Trojan	4	4	763.888888888889	Jaden
1308	225	1.35	0	6	Trojan	3	4	458.3333333333333	Jaden
1315	250	3	2	12	Zumax or oem	2	2	436.50793650793645	Jaden
1316	400	20.48	3	48	Buaman	2	1	653.0501089324619	Jaden
1352	400	20.48	3	48	Buaman	12	1	4493.464052287582	Jaden
1353	300	15.36	3	48	Teze	1	1	269.6078431372549	Jaden
1403	300	15.36	3	48	Teze	1	1	269.6078431372549	Jaden
1452	400	20.48	3	48	Buaman	2	1	599.1285403050109	Jaden
1502	100	1.2	3	12	Renogy	1	4	89.86928104575163	Jaden
\.


--
-- TOC entry 5040 (class 0 OID 41046)
-- Dependencies: 237
-- Data for Name: breaker_specs; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.breaker_specs (breaker_id, calculated_capacity, current, maximum_voltage, model, type, creator) FROM stdin;
802	43	60	400	OM1-251	DC	Jaden
952	57	60	400	OM1-251	DC	Jaden
955	43	60	400	OM1-251	DC	Jaden
956	43	60	400	OM1-251	DC	Jaden
957	57	60	400	OM1-251	DC	Jaden
959	57	60	400	OM1-251	DC	Jaden
1003	57	60	400	OM1-251	DC	Jaden
1202	0	25	500	ABB S203-DC25	DC	Jaden
1203	29	40	400	OM1-250	DC	Jaden
1252	29	40	400	OM1-250	DC	Jaden
1258	29	40	400	OM1-250	DC	Jaden
1265	22	25	500	ABB S203-DC25	DC	Jaden
1266	57	60	400	OM1-251	DC	Jaden
1302	257	\N	\N	\N	\N	Jaden
1303	57	60	400	OM1-251	DC	Jaden
1353	43	60	400	OM1-251	DC	Jaden
1402	29	40	400	OM1-250	DC	Jaden
1452	29	25	500	ABB S203-DC25	DC	Jaden
\.


--
-- TOC entry 5027 (class 0 OID 16452)
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
-- TOC entry 5041 (class 0 OID 41053)
-- Dependencies: 238
-- Data for Name: controller_specs; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.controller_specs (controller_id, brand, calculated_capacity, parallel, series, maximum_charge_current, maximum_volts, minimum_volts, model, type, creator) FROM stdin;
802	Lumiax	38	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
952	Lumiax	50	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
955	Lumiax	38	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
956	Lumiax	38	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
957	Lumiax	50	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
959	Lumiax	50	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
1003	Lumiax	50	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
1202	Lumiax	0	1	1	10	130	45	MT1050-EU	MPPT	Jaden
1203	Lumiax	25	1	1	30	780	100	MT3010	MPPT	Jaden
1252	Lumiax	25	1	1	30	780	100	MT3010	MPPT	Jaden
1258	Lumiax	25	1	1	30	780	100	MT3010	MPPT	Jaden
1265	Lumiax	19	1	1	20	520	100	MT2010	MPPT	Jaden
1266	Lumiax	50	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
1302	Lumiax	227	23	1	10	130	45	MT1050-EU	MPPT	Jaden
1303	Lumiax	50	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
1353	Lumiax	38	1	1	60	750	190	MT6020-Pro	MPPT	Jaden
1402	Lumiax	26	1	1	30	780	100	MT3010	MPPT	Jaden
1452	Lumiax	26	1	1	30	780	100	MT3010	MPPT	Jaden
\.


--
-- TOC entry 5025 (class 0 OID 16443)
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
-- TOC entry 5046 (class 0 OID 65536)
-- Dependencies: 243
-- Data for Name: creator_token; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.creator_token (token_id, created_at, expires_at, is_revoked, is_used, creator_username) FROM stdin;
bd5a851c-6956-4be5-b933-8bfd69c5255c	2025-06-29 16:20:14.486541+01	2025-06-30 16:20:14.486+01	t	f	Jaden
956c34e9-4b57-439f-83c3-a5fb74c8425d	2025-06-28 12:43:22.654344+01	2025-06-29 12:43:22.654+01	t	f	Jaden
c7d25461-0f4c-429a-a5d2-b858087d010b	2025-06-28 12:58:50.27718+01	2025-06-29 12:58:50.277+01	t	t	Jaden
096f0c99-2dfb-4cdc-be97-5ebd49a40850	2025-06-28 13:05:20.440171+01	2025-06-29 13:05:20.44+01	t	f	Jaden
af463467-bc88-4067-ad5e-d460429c53c9	2025-06-28 13:15:39.677199+01	2025-06-29 13:15:39.677+01	t	t	Jaden
86732700-2269-4112-8092-5dfd22107398	2025-06-28 13:57:25.746946+01	2025-06-29 13:57:25.746+01	t	t	Jaden
73a990c3-597d-4880-8d0b-0054ad594649	2025-06-28 14:13:20.742466+01	2025-06-29 14:13:20.742+01	f	t	Jaden
4f416337-fa72-478d-96e3-b55ae0ab2e2c	2025-06-28 14:26:57.265061+01	2025-06-29 14:26:57.265+01	f	t	Jaden
556bfc42-a51a-43fa-86aa-f415a57fa94f	2025-06-28 14:28:33.608558+01	2025-06-29 14:28:33.608+01	f	t	Jaden
ef2e36a1-47f8-4b4f-97fa-f8e967076c40	2025-06-28 14:30:25.163185+01	2025-06-29 14:30:25.163+01	f	t	Jaden
d570f63b-cafa-4209-8a57-2b36a23e5dc2	2025-06-28 14:32:03.426939+01	2025-06-29 14:32:03.426+01	f	t	Jaden
0edf5eec-201c-4ecf-b127-f74a940a1a9d	2025-06-28 14:14:06.015687+01	2025-06-29 14:14:06.015+01	t	f	Jaden
da42f0ec-5447-4841-a35e-a31a400a2ab1	2025-06-28 14:15:16.664327+01	2025-06-29 14:15:16.664+01	t	f	Jaden
1b6f84a0-f638-4ac4-9a4d-c7315b352db6	2025-06-28 14:35:00.431918+01	2025-06-29 14:35:00.431+01	t	f	Jaden
2fb1747b-744b-4278-862e-6980f4824518	2025-06-28 14:40:16.835883+01	2025-06-29 14:40:16.835+01	t	f	Jaden
8d907c9e-b834-4247-81b2-1d08149dc842	2025-06-28 14:54:07.73547+01	2025-06-29 14:54:07.735+01	f	t	Jaden
afbb8448-a154-4050-9dbe-a6809650236f	2025-06-28 15:10:01.923267+01	2025-06-29 15:10:01.923+01	f	t	Jaden
57e2aa89-76e6-493d-ba15-e17c6f0a4182	2025-06-28 15:13:08.317161+01	2025-06-29 15:13:08.317+01	f	t	Jaden
a58cf37f-2657-4dca-9dae-b946e20fd985	2025-06-28 15:19:38.638356+01	2025-06-29 15:19:38.638+01	f	t	Jaden
eefc0142-ba22-48d0-a134-131774a4fcc3	2025-06-28 15:54:12.26778+01	2025-06-29 15:54:12.267+01	f	t	Jaden
78093f5e-abc5-48c8-8900-6d272eea8afb	2025-06-28 16:09:18.520484+01	2025-06-29 16:09:18.52+01	f	t	Jaden
7d7b6935-1de5-4c7f-840c-5d0271b70738	2025-06-28 16:43:35.961482+01	2025-06-29 16:43:35.961+01	f	t	Jaden
cfcd2916-86e5-4033-88d2-051ecaeb22ad	2025-06-28 17:35:06.581935+01	2025-06-29 17:35:06.581+01	f	t	Jaden
429b6aa1-24df-4ca6-b939-dd7147793521	2025-06-29 14:43:13.136727+01	2025-06-30 14:43:13.137+01	t	f	Jaden
7e5062bd-0c68-4444-b5a9-02d8fdf8f965	2025-06-29 15:09:51.826465+01	2025-06-30 15:09:51.826+01	t	f	Jaden
abe23aef-340d-4b05-ab44-f6d8fc3d8757	2025-06-29 18:18:04.566934+01	2025-06-30 18:18:04.566+01	f	t	Jaden
537420b4-3d15-4076-a1a1-e33874bcdbee	2025-06-29 18:16:33.323694+01	2025-06-30 18:16:33.323+01	f	t	Jaden
cd2af344-24d7-46a0-8aee-43085d897aaf	2025-06-29 18:38:40.980036+01	2025-06-30 18:38:40.98+01	f	f	Jaden
5c81277a-6d2d-44a7-8ef5-a1742dd9e8a9	2025-06-28 14:08:23.67344+01	2025-06-29 14:08:23.673+01	t	t	Jaden
e28b5641-42f7-4109-82f0-b6be1a5edb8c	2025-06-28 12:54:54.576481+01	2025-06-29 12:54:54.576+01	t	t	Jaden
9ec59e9e-714b-4d66-9494-1e1a03a945ad	2025-06-28 13:02:21.483122+01	2025-06-29 13:02:21.483+01	t	t	Jaden
1f0a88c9-967b-438c-824a-00ab88466bbd	2025-06-28 13:06:07.24984+01	2025-06-29 13:06:07.249+01	t	t	Jaden
300d38ef-46ac-440e-bd0a-9772bd058c8d	2025-06-28 13:12:19.291275+01	2025-06-29 13:12:19.292+01	t	t	Jaden
f54c7a9c-37ca-4a19-ae77-d7098bebb206	2025-06-28 13:27:08.492172+01	2025-06-29 13:27:08.492+01	t	t	Jaden
361cabd6-f91f-43dc-8a58-e59c54faf35f	2025-06-28 14:09:50.428189+01	2025-06-29 14:09:50.428+01	t	f	Jaden
e1bad48b-2ccc-498b-93eb-2ca08fbd551c	2025-06-28 12:03:49.08701+01	2025-06-29 12:03:49.087+01	t	t	Jaden
51e02011-63d2-4800-a3d5-c14fad8e2701	2025-06-28 12:23:37.643289+01	2025-06-29 12:23:37.643+01	t	t	Jaden
93d0c642-9363-4560-b13e-7774e8f29d25	2025-06-28 12:28:13.564332+01	2025-06-29 12:28:13.564+01	t	t	Jaden
9ad8ddef-1763-412a-99b7-c4b9aa1366f4	2025-06-28 14:20:34.274728+01	2025-06-29 14:20:34.274+01	f	t	Jaden
1661d46d-59e6-4dc3-b02c-838dd7c166d3	2025-06-28 14:33:06.6921+01	2025-06-29 14:33:06.692+01	f	t	Jaden
dbeb8f56-9177-4355-ba9a-191c946e477f	2025-06-28 14:21:59.175422+01	2025-06-29 14:21:59.175+01	t	f	Jaden
7238dd7c-18a7-4c72-9c16-49d031292bd9	2025-06-28 14:38:19.862189+01	2025-06-29 14:38:19.862+01	f	t	Jaden
b6e8b1ee-2ad2-4dd4-a36a-1d37b2109b67	2025-06-28 14:57:14.133555+01	2025-06-29 14:57:14.133+01	f	t	Jaden
1e4278e2-6268-4991-a3b5-adddbecd7b71	2025-06-28 15:01:11.024064+01	2025-06-29 15:01:11.024+01	f	t	Jaden
4a5757ae-6542-4ce3-b251-cbbe7cf9d408	2025-06-28 15:03:11.44414+01	2025-06-29 15:03:11.444+01	f	t	Jaden
1c417fb9-17e6-455a-b07d-ee9efe8b996b	2025-06-28 15:14:53.095886+01	2025-06-29 15:14:53.095+01	f	t	Jaden
2497d777-8caf-46e8-aff7-5a55fcf4d6fe	2025-06-28 15:36:07.185217+01	2025-06-29 15:36:07.185+01	f	t	Jaden
a5a406e2-8c8c-4183-9092-f4b5a44c5acf	2025-06-28 16:22:45.577734+01	2025-06-29 16:22:45.577+01	f	t	Jaden
0fe97ef3-f110-4a80-8c72-2beff038f4e8	2025-06-28 17:09:42.723763+01	2025-06-29 17:09:42.723+01	f	t	Jaden
bcfc14d1-98a0-490a-8644-2ba69f5de2f2	2025-06-28 18:09:06.53916+01	2025-06-29 18:09:06.539+01	f	t	Jaden
d4a2e13e-5e9a-4308-a984-55beccbcfdae	2025-06-29 14:44:23.190631+01	2025-06-30 14:44:23.19+01	f	t	Jaden
\.


--
-- TOC entry 5042 (class 0 OID 41060)
-- Dependencies: 239
-- Data for Name: inverter_specs; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.inverter_specs (inverter_id, calculated_inverter_capacity_kva, capacity_kva, parallel, series, model, name, system_voltage, type, creator) FROM stdin;
802	5.25	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
952	5.25	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
955	5.25	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
956	9.75	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
957	9.75	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
959	9.75	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
1003	8.25	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
1202	0	3.6	1	1	AW-56	byx	48	Sine	Jaden
1203	4.5	5	1	1	AW-56	byx	48	Sine	Jaden
1252	4.5	5	1	1	AW-56	byx	24	Sine	Jaden
1258	4.5	5	1	1	AW-56	byx	24	Sine	Jaden
1265	1.5	2.5	1	1	WTHD-LI-2K	WTHD	24	Sine	Jaden
1266	1.95	3.6	1	1	AW-56	byx	48	Sine	Jaden
1302	1.5	3.6	1	1	AW-56	byx	48	Sine	Jaden
1303	8.25	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
1353	8.25	10	1	1	WTHD-LI-10K	WTHD	48	Sine	Jaden
1402	1.5	3.6	1	1	AW-56	byx	48	Sine	Jaden
1452	3	2.5	2	1	WTHD-LI-2K	byx	24	Sine	Jaden
\.


--
-- TOC entry 5021 (class 0 OID 16425)
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
-- TOC entry 5023 (class 0 OID 16434)
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
-- TOC entry 5043 (class 0 OID 49152)
-- Dependencies: 240
-- Data for Name: reports; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.reports (id, created_at, report, username, system_report_id) FROM stdin;
652	2025-06-28 11:46:44.18628+01	{"creator": {"role": "USER", "password": "$2a$10$jO6Qq0n.mH60ApHSMrgG8./GbMQ1dHzP8U8nAdKKtT9Z/TicA4BS6", "username": "Jaden"}, "request": {"requestId": {"psh": 5.0, "title": "FRSC", "load_w": 2000.0, "energy_wh": 3000.0, "batteryType": "LITHIUM", "systemVolts": 48, "daysOfBackup": 1.0, "selectedPanel": {"brand": "Canadian Solar", "power": 450}, "arraySeriesLength": 2}}, "inverter": {"name": "byx", "type": "Sine", "model": "AW-56", "inverterId": 1452, "capacityKva": 3.6, "configuration": {"total": 1, "series": 1, "parallel": 1}, "systemVoltage": 48, "calculatedInverterCapacityKva": 3.0}, "reportId": 1502, "updateAt": null, "createdAt": 1751107604.143519300, "dcBreaker": {"type": "DC", "model": "OM1-250", "current": 40, "breakerId": 1452, "maximumVoltage": 400, "calculatedCapacity": 29.0}, "solarArray": {"brand": "Canadian Solar", "model": "CS6.2-66TB-595", "arrayId": 1452, "configuration": {"total": 4, "series": 2, "parallel": 2}, "electricalProperties": {"Imp": 11.84, "Isc": 12.73, "Voc": 44.9, "Vmax": 38.0, "type": "MONO", "power_w": 450}, "mechanicalProperties": {"width_m": 1.134, "length_m": 2.382}, "calculatePanelCapacity": 923.0769230769231}, "batteryBank": {"brand": "Renogy", "batteryId": 1502, "batteryType": "LITHIUM", "configuration": {"total": 4, "series": 4, "parallel": 1}, "batteryVoltage": 12.0, "requiredBankCapacityAh": 89.86928104575163, "batteryEnergyCapacityAh": 1.2, "batteryCurrentCapacityAh": 100}, "wireDetails": [{"type": "COPPER", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 1214.487885893838}, {"type": "ALUMINUM", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 769.9394899251502}], "chargeController": {"type": "MPPT", "brand": "Lumiax", "model": "MT3010", "controllerId": 1452, "maximumVolts": 780, "minimumVolts": 100, "configuration": {"total": 1, "series": 1, "parallel": 1}, "calculatedCapacity": 26.0, "maximumChargeCurrent": 30}}	Jaden	1502
602	2025-06-21 20:52:38.703296+01	{"creator": {"role": "USER", "password": "$2a$10$jO6Qq0n.mH60ApHSMrgG8./GbMQ1dHzP8U8nAdKKtT9Z/TicA4BS6", "username": "Jaden"}, "request": {"requestId": {"psh": 6.0, "title": "This is a test", "load_w": 1000.0, "energy_wh": 10000.0, "batteryType": "LITHIUM", "systemVolts": 48, "daysOfBackup": 2.0, "selectedPanel": {"brand": "Canadian Solar", "power": 450}, "arraySeriesLength": 3}}, "inverter": {"name": "byx", "type": "Sine", "model": "AW-56", "inverterId": 1402, "capacityKva": 3.6, "configuration": {"total": 1, "series": 1, "parallel": 1}, "systemVoltage": 48, "calculatedInverterCapacityKva": 1.5}, "reportId": 1452, "updateAt": null, "createdAt": 1750535558.578150100, "dcBreaker": {"type": "DC", "model": "OM1-250", "current": 40, "breakerId": 1402, "maximumVoltage": 400, "calculatedCapacity": 29.0}, "solarArray": {"brand": "Canadian Solar", "model": "CS6.2-66TB-595", "arrayId": 1402, "configuration": {"total": 6, "series": 3, "parallel": 2}, "electricalProperties": {"Imp": 11.84, "Isc": 12.73, "Voc": 44.9, "Vmax": 38.0, "type": "MONO", "power_w": 450}, "mechanicalProperties": {"width_m": 1.134, "length_m": 2.382}, "calculatePanelCapacity": 2564.102564102564}, "batteryBank": {"brand": "Buaman", "batteryId": 1452, "batteryType": "LITHIUM", "configuration": {"total": 2, "series": 1, "parallel": 2}, "batteryVoltage": 48.0, "requiredBankCapacityAh": 599.1285403050109, "batteryEnergyCapacityAh": 20.48, "batteryCurrentCapacityAh": 400}, "wireDetails": [{"type": "COPPER", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 1821.7318288407569}, {"type": "ALUMINUM", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 1154.9092348877252}], "chargeController": {"type": "MPPT", "brand": "Lumiax", "model": "MT3010", "controllerId": 1402, "maximumVolts": 780, "minimumVolts": 100, "configuration": {"total": 1, "series": 1, "parallel": 1}, "calculatedCapacity": 26.0, "maximumChargeCurrent": 30}}	Jaden	1452
\.


--
-- TOC entry 5045 (class 0 OID 49178)
-- Dependencies: 242
-- Data for Name: request; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.request (array_series_length, battery_type, days_of_backup, energy_wh, load_w, psh, brand, power, system_volts, title) FROM stdin;
3	LITHIUM	2	10000	1000	6	Canadian Solar	450	48	This is a test
2	LITHIUM	1	3000	2000	5	Canadian Solar	450	48	FRSC
\.


--
-- TOC entry 5044 (class 0 OID 49159)
-- Dependencies: 241
-- Data for Name: system_report; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.system_report (report_id, created_at, update_at, wire_details, battery_bank, controller, creator, dc_breaker, inverter, array_series, battery_type, days_of_backup, energy_wh, load_w, psh, panel_brand, panel_power, sys_volts, solar_array, title) FROM stdin;
1452	2025-06-21 20:52:38.57815+01	\N	[{"type": "COPPER", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 1821.7318288407569}, {"type": "ALUMINUM", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 1154.9092348877252}]	1452	1402	Jaden	1402	1402	3	LITHIUM	2	10000	1000	6	Canadian Solar	450	48	1402	This is a test
1502	2025-06-28 11:46:44.143519+01	\N	[{"type": "COPPER", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 1214.487885893838}, {"type": "ALUMINUM", "powerLoss": 0.02, "lengthType": "Round-Trip", "recommendation": "Minimize cost by keeping cable length below 50m", "wireSpecification": {"SWG": "SWG8", "maxCurrent": 26.5, "diameter_mm": 4.064}, "maximumAllowedWireLength_m": 769.9394899251502}]	1502	1452	Jaden	1452	1452	2	LITHIUM	1	3000	2000	5	Canadian Solar	450	48	1452	FRSC
\.


--
-- TOC entry 5030 (class 0 OID 24576)
-- Dependencies: 227
-- Data for Name: users; Type: TABLE DATA; Schema: solar_inventory; Owner: postgres
--

COPY solar_inventory.users (username, role, password) FROM stdin;
Jaden	USER	$2a$10$jO6Qq0n.mH60ApHSMrgG8./GbMQ1dHzP8U8nAdKKtT9Z/TicA4BS6
\.


--
-- TOC entry 5057 (class 0 OID 0)
-- Dependencies: 228
-- Name: array_specs_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.array_specs_seq', 1501, true);


--
-- TOC entry 5058 (class 0 OID 0)
-- Dependencies: 225
-- Name: batteries_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.batteries_id_seq', 23, true);


--
-- TOC entry 5059 (class 0 OID 0)
-- Dependencies: 229
-- Name: battery_specs_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.battery_specs_seq', 1551, true);


--
-- TOC entry 5060 (class 0 OID 0)
-- Dependencies: 230
-- Name: breaker_specs_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.breaker_specs_seq', 1501, true);


--
-- TOC entry 5061 (class 0 OID 0)
-- Dependencies: 223
-- Name: breakers_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.breakers_id_seq', 23, true);


--
-- TOC entry 5062 (class 0 OID 0)
-- Dependencies: 231
-- Name: controller_specs_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.controller_specs_seq', 1501, true);


--
-- TOC entry 5063 (class 0 OID 0)
-- Dependencies: 221
-- Name: controllers_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.controllers_id_seq', 16, true);


--
-- TOC entry 5064 (class 0 OID 0)
-- Dependencies: 232
-- Name: inverter_specs_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.inverter_specs_seq', 1501, true);


--
-- TOC entry 5065 (class 0 OID 0)
-- Dependencies: 217
-- Name: inverters_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.inverters_id_seq', 13, true);


--
-- TOC entry 5066 (class 0 OID 0)
-- Dependencies: 219
-- Name: panels_id_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.panels_id_seq', 12, true);


--
-- TOC entry 5067 (class 0 OID 0)
-- Dependencies: 233
-- Name: reports_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.reports_seq', 701, true);


--
-- TOC entry 5068 (class 0 OID 0)
-- Dependencies: 234
-- Name: system_report_seq; Type: SEQUENCE SET; Schema: solar_inventory; Owner: postgres
--

SELECT pg_catalog.setval('solar_inventory.system_report_seq', 1551, true);


--
-- TOC entry 4829 (class 2606 OID 41037)
-- Name: array_specs array_specs_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.array_specs
    ADD CONSTRAINT array_specs_pkey PRIMARY KEY (array_id);


--
-- TOC entry 4825 (class 2606 OID 16468)
-- Name: batteries batteries_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.batteries
    ADD CONSTRAINT batteries_pkey PRIMARY KEY (id);


--
-- TOC entry 4831 (class 2606 OID 41045)
-- Name: battery_specs battery_specs_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.battery_specs
    ADD CONSTRAINT battery_specs_pkey PRIMARY KEY (battery_id);


--
-- TOC entry 4833 (class 2606 OID 41052)
-- Name: breaker_specs breaker_specs_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.breaker_specs
    ADD CONSTRAINT breaker_specs_pkey PRIMARY KEY (breaker_id);


--
-- TOC entry 4823 (class 2606 OID 16459)
-- Name: breakers breakers_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.breakers
    ADD CONSTRAINT breakers_pkey PRIMARY KEY (id);


--
-- TOC entry 4835 (class 2606 OID 41059)
-- Name: controller_specs controller_specs_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.controller_specs
    ADD CONSTRAINT controller_specs_pkey PRIMARY KEY (controller_id);


--
-- TOC entry 4821 (class 2606 OID 16450)
-- Name: controllers controllers_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.controllers
    ADD CONSTRAINT controllers_pkey PRIMARY KEY (id);


--
-- TOC entry 4859 (class 2606 OID 65542)
-- Name: creator_token creator_token_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.creator_token
    ADD CONSTRAINT creator_token_pkey PRIMARY KEY (token_id);


--
-- TOC entry 4837 (class 2606 OID 41066)
-- Name: inverter_specs inverter_specs_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.inverter_specs
    ADD CONSTRAINT inverter_specs_pkey PRIMARY KEY (inverter_id);


--
-- TOC entry 4817 (class 2606 OID 16432)
-- Name: inverters inverters_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.inverters
    ADD CONSTRAINT inverters_pkey PRIMARY KEY (id);


--
-- TOC entry 4819 (class 2606 OID 16441)
-- Name: panels panels_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.panels
    ADD CONSTRAINT panels_pkey PRIMARY KEY (id);


--
-- TOC entry 4839 (class 2606 OID 49158)
-- Name: reports reports_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.reports
    ADD CONSTRAINT reports_pkey PRIMARY KEY (id);


--
-- TOC entry 4857 (class 2606 OID 49184)
-- Name: request request_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.request
    ADD CONSTRAINT request_pkey PRIMARY KEY (array_series_length, battery_type, days_of_backup, energy_wh, load_w, psh, brand, power, system_volts);


--
-- TOC entry 4841 (class 2606 OID 49165)
-- Name: system_report system_report_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT system_report_pkey PRIMARY KEY (report_id);


--
-- TOC entry 4843 (class 2606 OID 57345)
-- Name: system_report uk648g0y75e7pe6e2xc8oxrw1px; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT uk648g0y75e7pe6e2xc8oxrw1px UNIQUE (array_series, battery_type, days_of_backup, energy_wh, load_w, psh, panel_brand, panel_power, sys_volts, title);


--
-- TOC entry 4845 (class 2606 OID 49171)
-- Name: system_report uk95e3yit9430ev5hwbhkdaqcfu; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT uk95e3yit9430ev5hwbhkdaqcfu UNIQUE (controller);


--
-- TOC entry 4847 (class 2606 OID 49175)
-- Name: system_report uka08twiyxg7gaxvnv4xs6hhsne; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT uka08twiyxg7gaxvnv4xs6hhsne UNIQUE (inverter);


--
-- TOC entry 4849 (class 2606 OID 49167)
-- Name: system_report ukjp2kridipy5axk1tir0rp5svq; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT ukjp2kridipy5axk1tir0rp5svq UNIQUE (array_series, battery_type, days_of_backup, energy_wh, load_w, psh, panel_brand, panel_power, sys_volts);


--
-- TOC entry 4851 (class 2606 OID 49177)
-- Name: system_report ukjrq7f7yt5ma7m442h9t36m174; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT ukjrq7f7yt5ma7m442h9t36m174 UNIQUE (solar_array);


--
-- TOC entry 4853 (class 2606 OID 49173)
-- Name: system_report ukk4937v3b4g54sk1cu2om9f5cf; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT ukk4937v3b4g54sk1cu2om9f5cf UNIQUE (dc_breaker);


--
-- TOC entry 4855 (class 2606 OID 49169)
-- Name: system_report ukt0nlbr6uv9kqpvx1l2m9uey8f; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT ukt0nlbr6uv9kqpvx1l2m9uey8f UNIQUE (battery_bank);


--
-- TOC entry 4827 (class 2606 OID 32951)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- TOC entry 4864 (class 2606 OID 41111)
-- Name: inverter_specs fk3er0q7lm29sfqilpeycmbx8jp; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.inverter_specs
    ADD CONSTRAINT fk3er0q7lm29sfqilpeycmbx8jp FOREIGN KEY (creator) REFERENCES solar_inventory.users(username);


--
-- TOC entry 4867 (class 2606 OID 49210)
-- Name: system_report fk4sfta85pg82q0if5ncwmcpy0x; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT fk4sfta85pg82q0if5ncwmcpy0x FOREIGN KEY (dc_breaker) REFERENCES solar_inventory.breaker_specs(breaker_id);


--
-- TOC entry 4865 (class 2606 OID 49185)
-- Name: reports fk687ncayqf7wcgofexvss02ubs; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.reports
    ADD CONSTRAINT fk687ncayqf7wcgofexvss02ubs FOREIGN KEY (username) REFERENCES solar_inventory.users(username);


--
-- TOC entry 4868 (class 2606 OID 49205)
-- Name: system_report fk6gtm8h3yys9jyl4fmswlrcyex; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT fk6gtm8h3yys9jyl4fmswlrcyex FOREIGN KEY (creator) REFERENCES solar_inventory.users(username);


--
-- TOC entry 4861 (class 2606 OID 41096)
-- Name: battery_specs fk8e39lil5muynun4dmum68ty66; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.battery_specs
    ADD CONSTRAINT fk8e39lil5muynun4dmum68ty66 FOREIGN KEY (creator) REFERENCES solar_inventory.users(username);


--
-- TOC entry 4860 (class 2606 OID 41091)
-- Name: array_specs fk97hlfsj9n4lpudn5mn0yysfq4; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.array_specs
    ADD CONSTRAINT fk97hlfsj9n4lpudn5mn0yysfq4 FOREIGN KEY (creator) REFERENCES solar_inventory.users(username);


--
-- TOC entry 4866 (class 2606 OID 49190)
-- Name: reports fkf6p6ywnoxnd01d8l97moy97d4; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.reports
    ADD CONSTRAINT fkf6p6ywnoxnd01d8l97moy97d4 FOREIGN KEY (system_report_id) REFERENCES solar_inventory.system_report(report_id);


--
-- TOC entry 4869 (class 2606 OID 49200)
-- Name: system_report fkfdden58if6y5grrwgnbud76ag; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT fkfdden58if6y5grrwgnbud76ag FOREIGN KEY (controller) REFERENCES solar_inventory.controller_specs(controller_id);


--
-- TOC entry 4863 (class 2606 OID 41106)
-- Name: controller_specs fkfinathx97blktf490qct27mep; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.controller_specs
    ADD CONSTRAINT fkfinathx97blktf490qct27mep FOREIGN KEY (creator) REFERENCES solar_inventory.users(username);


--
-- TOC entry 4870 (class 2606 OID 49225)
-- Name: system_report fkh0vr0cmtfco66fiit25ivfdic; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT fkh0vr0cmtfco66fiit25ivfdic FOREIGN KEY (solar_array) REFERENCES solar_inventory.array_specs(array_id);


--
-- TOC entry 4871 (class 2606 OID 49215)
-- Name: system_report fkhu6banqogojkinmbyklo3fe0h; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT fkhu6banqogojkinmbyklo3fe0h FOREIGN KEY (inverter) REFERENCES solar_inventory.inverter_specs(inverter_id);


--
-- TOC entry 4874 (class 2606 OID 65543)
-- Name: creator_token fkjfqbkgblop2sxhh1hic5wh609; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.creator_token
    ADD CONSTRAINT fkjfqbkgblop2sxhh1hic5wh609 FOREIGN KEY (creator_username) REFERENCES solar_inventory.users(username);


--
-- TOC entry 4872 (class 2606 OID 49195)
-- Name: system_report fkkj8axhc2vxhrk8oem3g4ds2hc; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT fkkj8axhc2vxhrk8oem3g4ds2hc FOREIGN KEY (battery_bank) REFERENCES solar_inventory.battery_specs(battery_id);


--
-- TOC entry 4873 (class 2606 OID 49220)
-- Name: system_report fklwuabl6eqdmssrsw4n903aj1b; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.system_report
    ADD CONSTRAINT fklwuabl6eqdmssrsw4n903aj1b FOREIGN KEY (array_series, battery_type, days_of_backup, energy_wh, load_w, psh, panel_brand, panel_power, sys_volts) REFERENCES solar_inventory.request(array_series_length, battery_type, days_of_backup, energy_wh, load_w, psh, brand, power, system_volts);


--
-- TOC entry 4862 (class 2606 OID 41101)
-- Name: breaker_specs fkskj0xlv5kbbbxrhou5tkljqs0; Type: FK CONSTRAINT; Schema: solar_inventory; Owner: postgres
--

ALTER TABLE ONLY solar_inventory.breaker_specs
    ADD CONSTRAINT fkskj0xlv5kbbbxrhou5tkljqs0 FOREIGN KEY (creator) REFERENCES solar_inventory.users(username);


-- Completed on 2025-07-01 10:31:42

--
-- PostgreSQL database dump complete
--


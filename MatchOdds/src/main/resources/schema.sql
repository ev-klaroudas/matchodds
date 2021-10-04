--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

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

--
-- Name: match_odds; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA match_odds;


ALTER SCHEMA match_odds OWNER TO postgres;

--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


--
-- Name: enum_sport; Type: TYPE; Schema: match_odds; Owner: postgres
--

CREATE TYPE match_odds.enum_sport AS ENUM (
    '1',
    '2'
);


ALTER TYPE match_odds.enum_sport OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: match_odds; Type: TABLE; Schema: match_odds; Owner: postgres
--

CREATE TABLE match_odds.match_odds (
    id bigint NOT NULL,
    match_id bigint NOT NULL,
    specifier character varying(20) NOT NULL,
    odd numeric(6,2) NOT NULL
);


ALTER TABLE match_odds.match_odds OWNER TO postgres;

--
-- Name: COLUMN match_odds.id; Type: COMMENT; Schema: match_odds; Owner: postgres
--

COMMENT ON COLUMN match_odds.match_odds.id IS 'identication column';


--
-- Name: COLUMN match_odds.match_id; Type: COMMENT; Schema: match_odds; Owner: postgres
--

COMMENT ON COLUMN match_odds.match_odds.match_id IS 'match identication';


--
-- Name: COLUMN match_odds.specifier; Type: COMMENT; Schema: match_odds; Owner: postgres
--

COMMENT ON COLUMN match_odds.match_odds.specifier IS 'odd type';


--
-- Name: COLUMN match_odds.odd; Type: COMMENT; Schema: match_odds; Owner: postgres
--

COMMENT ON COLUMN match_odds.match_odds.odd IS 'odd value';


--
-- Name: match_odds_id_seq; Type: SEQUENCE; Schema: match_odds; Owner: postgres
--

ALTER TABLE match_odds.match_odds ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME match_odds.match_odds_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: matches; Type: TABLE; Schema: match_odds; Owner: postgres
--

CREATE TABLE match_odds.matches (
    id bigint NOT NULL,
    description character varying(500) NOT NULL,
    match_date date NOT NULL,
    match_time time with time zone NOT NULL,
    team_a character varying(80) NOT NULL,
    team_b character varying(80) NOT NULL,
    sport match_odds.enum_sport NOT NULL
);


ALTER TABLE match_odds.matches OWNER TO postgres;

--
-- Name: COLUMN matches.id; Type: COMMENT; Schema: match_odds; Owner: postgres
--

COMMENT ON COLUMN match_odds.matches.id IS 'identication column';


--
-- Name: matches_id_seq; Type: SEQUENCE; Schema: match_odds; Owner: postgres
--

ALTER TABLE match_odds.matches ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME match_odds.matches_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: match_odds; Type: TABLE DATA; Schema: match_odds; Owner: postgres
--

COPY match_odds.match_odds (id, match_id, specifier, odd) FROM stdin;
\.


--
-- Data for Name: matches; Type: TABLE DATA; Schema: match_odds; Owner: postgres
--

COPY match_odds.matches (id, description, match_date, match_time, team_a, team_b, sport) FROM stdin;
\.


--
-- Name: match_odds_id_seq; Type: SEQUENCE SET; Schema: match_odds; Owner: postgres
--

SELECT pg_catalog.setval('match_odds.match_odds_id_seq', 1, false);


--
-- Name: matches_id_seq; Type: SEQUENCE SET; Schema: match_odds; Owner: postgres
--

SELECT pg_catalog.setval('match_odds.matches_id_seq', 1, false);


--
-- Name: match_odds_id_idx; Type: INDEX; Schema: match_odds; Owner: postgres
--

CREATE UNIQUE INDEX match_odds_id_idx ON match_odds.match_odds USING btree (id);


--
-- Name: match_odds_match_id_idx; Type: INDEX; Schema: match_odds; Owner: postgres
--

CREATE INDEX match_odds_match_id_idx ON match_odds.match_odds USING btree (match_id);


--
-- Name: matches_id_idx; Type: INDEX; Schema: match_odds; Owner: postgres
--

CREATE UNIQUE INDEX matches_id_idx ON match_odds.matches USING btree (id);


--
-- Name: match_odds fk_match_odds_matches; Type: FK CONSTRAINT; Schema: match_odds; Owner: postgres
--

ALTER TABLE ONLY match_odds.match_odds
    ADD CONSTRAINT fk_match_odds_matches FOREIGN KEY (match_id) REFERENCES match_odds.matches(id);


--
-- PostgreSQL database dump complete
--


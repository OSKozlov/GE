CREATE KEYSPACE timeseries WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor':1 };
use timeseries;
CREATE TABLE TimeSeriesData (
    guid int PRIMARY KEY,
    Timestamp timestamp,
    type varchar,
    value float,
    fileName varchar);
CREATE TABLE StatData (
    fileName varchar PRIMARY KEY,
    messageCount int,
    maxTemperature float,
    averageVoltage float);
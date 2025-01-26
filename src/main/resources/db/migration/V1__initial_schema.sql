-- Create Users table
CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Accounts table
CREATE TABLE Accounts (
                          id SERIAL PRIMARY KEY,
                          user_id INT REFERENCES Users(id),
                          balance DECIMAL(15, 2) NOT NULL,
                          account_type VARCHAR(50),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Instruments table
CREATE TABLE Instruments (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             symbol VARCHAR(10) UNIQUE NOT NULL,
                             type VARCHAR(50) NOT NULL
);

-- Create Trades table
CREATE TABLE Trades (
                        id SERIAL PRIMARY KEY,
                        user_id INT REFERENCES Users(id),
                        instrument_id INT REFERENCES Instruments(id),
                        trade_type VARCHAR(10) NOT NULL,
                        quantity INT NOT NULL,
                        price DECIMAL(15, 2) NOT NULL,
                        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create MarketData table
CREATE TABLE MarketData (
                            id SERIAL PRIMARY KEY,
                            instrument_id INT REFERENCES Instruments(id),
                            price DECIMAL(15, 2) NOT NULL,
                            volume INT NOT NULL,
                            timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_username ON Users(username);
CREATE INDEX idx_email ON Users(email);
CREATE INDEX idx_symbol ON Instruments(symbol);
CREATE INDEX idx_marketdata_instrument_timestamp ON MarketData(instrument_id, timestamp);

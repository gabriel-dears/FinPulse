-- V3 Flyway Migration: Update the Database Schema

-- Add a new 'last_updated' column to the Users table for tracking updates
ALTER TABLE Users
    ADD COLUMN last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Add a new 'currency' column to the Accounts table to specify the account currency
ALTER TABLE Accounts
    ADD COLUMN currency VARCHAR(10) DEFAULT 'USD';

-- Add a new 'description' column to the Instruments table for additional details
ALTER TABLE Instruments
    ADD COLUMN description TEXT;

-- Add a 'trade_status' column to the Trades table to track the status of each trade
ALTER TABLE Trades
    ADD COLUMN trade_status VARCHAR(20) DEFAULT 'PENDING';

-- Add a new 'high_price' and 'low_price' columns to the MarketData table
ALTER TABLE MarketData
    ADD COLUMN high_price DECIMAL(15, 2),
ADD COLUMN low_price DECIMAL(15, 2);

-- Create new indexes for performance improvements
CREATE INDEX idx_accounts_user_currency ON Accounts(user_id, currency);
CREATE INDEX idx_trades_status_timestamp ON Trades(trade_status, timestamp);

-- Update existing constraints for better data integrity
ALTER TABLE Accounts
    ADD CONSTRAINT chk_balance_positive CHECK (balance >= 0);

ALTER TABLE Trades
    ADD CONSTRAINT chk_quantity_positive CHECK (quantity > 0),
ADD CONSTRAINT chk_price_positive CHECK (price > 0);

-- Modify the Instruments table to ensure the 'symbol' is always stored in uppercase
CREATE OR REPLACE FUNCTION enforce_uppercase_symbol()
RETURNS TRIGGER AS $$
BEGIN
    NEW.symbol = UPPER(NEW.symbol);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_uppercase_symbol ON Instruments;
CREATE TRIGGER trg_uppercase_symbol
    BEFORE INSERT OR UPDATE ON Instruments
                         FOR EACH ROW
                         EXECUTE FUNCTION enforce_uppercase_symbol();

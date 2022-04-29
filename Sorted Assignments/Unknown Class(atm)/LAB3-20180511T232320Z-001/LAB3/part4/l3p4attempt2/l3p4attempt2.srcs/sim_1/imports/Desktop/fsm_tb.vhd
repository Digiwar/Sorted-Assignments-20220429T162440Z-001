library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.numeric_std.all;

entity fsm_tb is
end;

architecture test of fsm_tb is

component fsm
port (
    clk: in std_logic;
    reset: in std_logic;
    R: out std_logic;
    G: out std_logic;
    Y: out std_logic);
end component;

signal clk : std_logic := '0';
signal reset : std_logic := '1';


signal R : std_logic;
signal G : std_logic;
signal Y : std_logic;

begin
    uut: fsm PORT MAP(
    clk => clk,
    reset => reset,
    R=>R,
    G=>G,
    Y=>Y
    );
        --port map(clk,reset,R,G,Y);
        
    clk_stimulus:  process 
    begin
        wait for 10 ns;
        clk <= not clk;
    end process;
    
    data_stimulus:  process 
    begin
        reset <= '0';
        wait for 300 ns;
    end process;
end test; 

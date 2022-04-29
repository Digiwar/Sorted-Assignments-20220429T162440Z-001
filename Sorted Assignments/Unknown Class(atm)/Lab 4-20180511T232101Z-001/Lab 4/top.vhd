library ieee;
use ieee.std_logic_1164.all;

entity top is

	port(
	
		sw1:    in std_logic;
		clk:    in std_logic;
		cin:    in std_logic;
		m:      in std_logic_vector(2 downto 0);
		dout:   out std_logic
		
	);
	
end top;

architecture final of top is 

	--//////////////////////////////////////////////////////////
	
	component fsm 
	port(
	
		reset:   in std_logic;
		clk:     in std_logic;
		clr:     out std_logic;
		w:       out std_logic_vector(2 downto 0);
		ce:      out std_logic_vector(3 downto 0);
		sel:     out std_logic_vector(1 downto 0);
		s:       out std_logic_vector(2 downto 0)
		
	);
	end component;
	
	--//////////////////////////////////////////////////////////
	
	component datapath
	port(
	
		clock:  in std_logic;
		clr:  in std_logic;
		cin:  in std_logic;
		m:    in std_logic_vector(2 downto 0);
		w:    in std_logic_vector(2 downto 0);
		ce:   in std_logic_vector(3 downto 0);
		sel:  in std_logic_vector(1 downto 0);
		s:    in std_logic_vector(2 downto 0);
		dout: out std_logic
		
	);
	end component;
	
	--//////////////////////////////////////////////////////////

signal clr:   std_logic;
signal w:     std_logic_vector(2 downto 0);
signal ce:    std_logic_vector(3 downto 0);
signal sel:   std_logic_vector(1 downto 0);
signal s:     std_logic_vector(2 downto 0);

begin

	g1: fsm      port map(sw1, clk, clr, w, ce, sel, s);
	g2: datapath port map(clk, clr, cin, m, w, ce, sel, s, dout);

end final;
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 

ENTITY datapath_tb IS
END datapath_tb;
 
ARCHITECTURE behavior OF datapath_tb IS 
 
    COMPONENT datapath
    PORT(
         clock : IN  std_logic;
         clr : IN  std_logic;
         cin : IN  std_logic;
         m : IN  std_logic_vector(2 downto 0);
         w : IN  std_logic_vector(2 downto 0);
         ce : IN  std_logic_vector(3 downto 0);
         sel : IN  std_logic_vector(1 downto 0);
         s : IN  std_logic_vector(2 downto 0);
         dout : OUT  std_logic
        );
    END COMPONENT;
    
   signal clock : std_logic := '0';
   signal clr : std_logic := '0';
   signal cin : std_logic := '0';
   signal m : std_logic_vector(2 downto 0) := (others => '0');
   signal w : std_logic_vector(2 downto 0) := (others => '0');
   signal ce : std_logic_vector(3 downto 0) := (others => '0');
   signal sel : std_logic_vector(1 downto 0) := (others => '0');
   signal s : std_logic_vector(2 downto 0) := (others => '0');

   signal dout : std_logic;

   constant clock_period : time := 10 ns;
 
BEGIN

--///////////////////////////////////////////////////
 
   uut: datapath PORT MAP (
          clock => clock,
          clr => clr,
          cin => cin,
          m => m,
          w => w,
          ce => ce,
          sel => sel,
          s => s,
          dout => dout
        );

--///////////////////////////////////////////

   clock_process :process
   begin
		clock <= '0';
		wait for clock_period/2;
		clock <= '1';
		wait for clock_period/2;
   end process;
 
--//////////////////////////////////////////

   -- Stimulus process
   stim_proc: process
   begin		
     
		clr <= '1';
		cin <= '0';
		m <= "000";
		w <= "000";
		ce <= "0000";
		sel <= "00";
		s <= "000";
      wait for 10 ns;	

		clr <= '0';
		cin <= '0';
		m <= "000";
		w <= "000";
		ce <= "0010";
		sel <= "01";
		s <= "010";
      wait for 10 ns;
		
		w <= "000";
		ce <= "1010";
		sel <= "01";
		s <= "010";
      wait for 10 ns;
		
		w <= "010";
		ce <= "0010";
		sel <= "00";
		s <= "010";
      wait for 10 ns;
		--wait; 
		
		w <= "000";
		ce <= "0001";
		sel <= "00";
		s <= "010";
      wait for 10 ns;
		
		w <= "000";
		ce <= "1001";
		sel <= "00";
		s <= "010";
      wait for 10 ns;
		--wait;
		
		w <= "001";
		ce <= "0001";
		sel <= "00";
		s <= "010";
      wait for 10 ns;
		
		w <= "000";
		ce <= "0000";
		sel <= "01";
		s <= "001";
      wait for 10 ns;
		-- wait;
		
		w <= "100";
		ce <= "1000";
		sel <= "01";
		s <= "001";
      wait for 10 ns;
		
		w <= "100";
		ce <= "0100";
		sel <= "01";
		s <= "001";
      wait for 10 ns;
		
		w <= "000";
		ce <= "0000";
		sel <= "01";
		s <= "001";
      wait for 10 ns;
		
		
		
		

      -- insert stimulus here 

      wait;
   end process;

END;

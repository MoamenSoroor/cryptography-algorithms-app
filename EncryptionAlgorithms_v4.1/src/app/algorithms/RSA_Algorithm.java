package app.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import app.core.Algorithm;

public class RSA_Algorithm extends Algorithm {
	
	public static String RSA_NAME = "RSA_Algorithm";

	public final static long MAX_KEY = (long) Math.pow(2, 31);
	public final static long MIN_KEY = (long) Math.pow(2, 16);
	public final static long MAX_N = MAX_KEY * MAX_KEY;
	public final static long MIN_N = MIN_KEY * MIN_KEY;
	
	private RSA_KeyGenerator generator; 
	private RSA_KeyList rsa_List;
	private int keyIndex = 1;
	private RSA_Key selectedKey;

	public RSA_Algorithm() {
		super(RSA_NAME);
	}

	public RSA_Algorithm(String name) {
		super(RSA_NAME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String encrypt(String key, String plainText) {
		long k = Long.parseLong(key);
		long m = Long.parseLong(plainText);
		return String.valueOf(selectedKey.encrypt(m, k));
	}

	@Override
	public String decrypt(String key, String cipherText) {
		long k = Long.parseLong(key);
		long c = Long.parseLong(cipherText);
		return String.valueOf(selectedKey.decrypt(c, k));
	}
	
	public void generateRSA_List() {
		generator = new RSA_KeyGenerator(2 , 500);
		generator.loadPrimes();
		rsa_List = generator.generateRSA_KeyList();
		selectedKey = rsa_List.get(keyIndex = 0); 
	}
	
	public String getRSA_KeyList() {
		return rsa_List.toString();
	}
	
	public boolean selectKey(int index) {
		if(index > 0 && index <= rsa_List.size()) {
			keyIndex = index - 1;
			selectedKey = rsa_List.get(keyIndex);

			return true;
		}
		else
			return false;
	}
	
	public void selectKey(String selectedItem) {
		selectedKey = rsa_List.getKey(selectedItem);
		
	}


	
	public RSA_Key getKey(int index) {
		return rsa_List.get(index);
	}

	public String[] getKeyNames() {
		return rsa_List.getKeyNames();
	}

	public RSA_Key getKey(String name) {
		return rsa_List.getKey(name);
	}
	
	public RSA_Key getSelectedKey() {
		return selectedKey;
	}

	public static class RSA_KeyGenerator{
		private int counter = 0;
		private long max =  MAX_KEY;
		private long min =  MIN_KEY;
		private long density = (max - min - 1);
		
		private List<Long> primes;
		
		public RSA_KeyGenerator() {
			
		}
		
		public RSA_KeyGenerator(long min, long max) {
			super();
			this.min = min <= 1 ? 2 : min;
			this.max = max;
			this.density = max - min - 1;
		}
		
		public RSA_KeyGenerator(long min , long max , long density) {
			super();
			this.min = min <= 1 ? 2 : min;
			this.max = max;
			this.density = density;
			if(density >= (max - min))
				this.density = max - min - 1;
		}


		public void loadPrimes() {
			primes = generatePrimeList(min, max, density);
		}
		
		public void loadPrimes(long density) {
			this.density = density;
			primes = generatePrimeList(min, max, this.density);
		}
		
		public RSA_Key generateRSA_Key() {
			if(counter >= primes.size() - 1)
				return null;
			long p = primes.get(counter++); 
			long q = primes.get(counter++);
			///System.out.println("p  " + p + " q = " + q);
			long n = p * q;
			long phi = (p - 1) * (q - 1);
			long e = findE(phi);
			long d = findD(e, phi);
			while(d == -1) {
				e = findE(phi);
				d = findD(e, phi);
				//System.out.println("e  " + e + " d = " + d);
			}
			return new RSA_Key(e, d, p, q);
			
		}
		
		public RSA_KeyList generateRSA_KeyList() {
			RSA_KeyList list = new RSA_KeyList();
			RSA_Key key;
			int counter = 0;
			do {
				 key = generateRSA_Key();
				 if(key != null) {
					 list.add(key);
					 key.setKeyName("Key-" + ++counter);
				 }

			}while(key != null);
			

			
			
			return list;
		}
		
		public String getPrimesListToString() {
			return primeListToString(primes);
		}
		
		public long findD(long e , long phi) {
			long i = phi + 1;
			for(i = phi + 1 ; i < MAX_N ; i+= phi) {
				if(i % e == 0)
					return i/e;
			}
			return -1;
			
		}
		
		
		public long findE(long e , long phi) {
			while(gcd(phi , e) != 1) {
				e = generateRandom(min, max);
			}
			return e;
		}
		
		public long findE(long phi) {
			long e = generateRandom(min, phi - 1);
			while(gcd(phi , e) != 1) {
				e = generateRandom(min, phi - 1);
			}
			return e;
		}
		
		
		
		
	}
	
	public static class RSA_Key{
		
		private String name = "Key";
		
		private long e , d;
		private long p , q;
		
		public RSA_Key(long e, long d, long p, long q) {
			super();
			this.e = e;
			this.d = d;
			this.p = p;
			this.q = q;
		}
		public long getE() {
			return e;
		}
		public long getD() {
			return d;
		}
		public long getP() {
			return p;
		}
		public long getQ() {
			return q;
		}
		
		public long getN() {
			return p * q;
		}
		
		public long getPhi() {
			return (p - 1)*( q - 1);
		}
		
		public String getKeyGeneratorsToString() {
			return  "generators { phi = "+ getPhi() +" , p = " + getP()+" , q = " + getQ() +" }";
		}
		
		
		public String getPublic() {
			return "PU { e = "+ e +" , n = " + getN() +" }";
		}
		
		public String getPrivate() {
			return "PR { d = "+ d +" , n = " + getN() +" }";
		}
		
		public long encrypt(long m , long k) {
			if( m >= getN())
				return -1;
			if(k == e)
				return encryptWithPU(m);
			else if(k == d)
				return encryptWithPR(m);
			else
				return -1;
			
		}
		
		public long decrypt(long c , long k) {
			if( c >= getN())
				return -1;
			if(k == e)
				return decryptWithPU(c);
			else if(k == d)
				return decryptWithPR(c);
			else
				return -1;
		}
		
		
		
		public long encryptWithPU(long m) {
			if( m >= getN())
				return -1;
			return BigInteger.valueOf(m).modPow(BigInteger.valueOf(e), BigInteger.valueOf(getN())).longValue();
		}
		
		public long encryptWithPR(long m) {
			if( m >= getN())
				return -1;
			return BigInteger.valueOf(m).modPow(BigInteger.valueOf(e), BigInteger.valueOf(getN())).longValue();
		}
		
		public long decryptWithPU(long c) {
			if( c >= getN())
				return -1;
			return BigInteger.valueOf(c).modPow(BigInteger.valueOf(d), BigInteger.valueOf(getN())).longValue();
		}
		
		public long decryptWithPR(long c) {
			if( c >= getN())
				return -1;
			return BigInteger.valueOf(c).modPow(BigInteger.valueOf(d), BigInteger.valueOf(getN())).longValue();
		}
		

		@Override
		public String toString() {
			return getKeyName() + ": " + getKeyGeneratorsToString() + System.lineSeparator() 
			+ getPublic() + System.lineSeparator() 
			+ getPrivate() + System.lineSeparator();
		}
		public String getKeyName() {
			return name;
		}
		public void setKeyName(String name) {
			this.name = name;
		}
		
		
		
	}
	
	public static class RSA_KeyList extends ArrayList<RSA_Key>{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("RSA_KeyList:" + System.lineSeparator());

			builder.append("---------------------------------------" + System.lineSeparator());
			for (int i = 0; i < this.size() ; i++) {
				builder.append("Key [" + (i + 1) +"]--->" + System.lineSeparator());
				builder.append(get(i) + System.lineSeparator());

			}
			builder.append("---------------------------------------" + System.lineSeparator());
			return builder.toString();
		}
		
		public String []  getKeyNames(){
			String [] names = new String[size()];
			for (int i = 0; i < this.size() ; i++) {
				names[i] = get(i).getKeyName();
			}
			return names;
		}
		
		public RSA_Key getKey(String name) {
			for(RSA_Key k : this) {
				if(name.equals(k.getKeyName()))
					return k;
			}
			return null;
		}
		
		
	}
/*	
	public static void main(String[] args) {
		
		RSA_KeyGenerator gen = new RSA_KeyGenerator(300 , 400);
		gen.loadPrimes();
		RSA_KeyList list = gen.generateRSA_KeyList();
		
		System.out.println(list);
		
		long c = list.get(0).encryptWithPU(15);
		System.out.println("m = 15");
		System.out.println("c = " + c);
		System.out.println("m = " + list.get(0).decryptWithPR(c));
		
	}
	*/
	

	
	
	// max should be less than 2 ^ 31 and min must be more than 0 
	public static List<Long> generatePrimeList(long min , long max){
		if(min > max || min < 0 || max > MAX_KEY)
			return null;
		List<Long> primes = new ArrayList<>();
		for(long i = min ; i < max ; i++) {
			if(i > MAX_KEY)
				break;
			if(isPrime(i))
				primes.add(i);
		}
		Collections.shuffle(primes);
		return primes;
	}
	
	// max should be less than 2 ^ 31 and min must be more than 0 
	public static List<Long> generatePrimeList(long min , long max , long density){
		if(min >= max || min < 0 || max > MAX_KEY) {
			//System.out.println("can not generate primes");
			return null;
		}
		long iterations = ((max - min)/density);
		/*
		System.out.println("den : " + density);
		System.out.println("interations : " + iterations);
		System.out.println("min = " + min);
		System.out.println("max = " + max);
		*/

		List<Long> primes = new ArrayList<>();
		while(primes.size() < density) {
			for(long i = min ; i <= max ; i+= iterations) {
				if(isPrime(i))
					primes.add(i);
				if(iterations <= 0)
					break;
			}
			iterations--;
			//System.out.println("interations : " + iterations);
			if(iterations <= 0)
				break;
		}
		//System.out.println("size of list = " + primes.size());
		Collections.shuffle(primes);
		return primes;
	}
	
	
	public static String primeListToString(List<Long> list) {
		StringBuilder builder = new StringBuilder();
		builder.append("List of Primes: " + System.lineSeparator());
		builder.append("----------------------------------" + System.lineSeparator());
		for(long g : list)
			builder.append(g + System.lineSeparator());
		builder.append("---------------------------------- " + System.lineSeparator());
		return builder.toString();
	}
	
	
	public static long generateRandom(long min , long max) {
		return (long)(Math.random() * (max - min + 1)  + min);

	}
	
	public static long GenerateRandomPrime(long min , long max) {
		long result = 0;
		boolean end = false;
		while(end == false) {
			result = (long)(Math.random() * (max - min + 1)  + min);
			///System.out.println(result);
			end = isPrime(result);
		}
		return result;
	}
	
	public static boolean isPrime(long n) {
		
		if(n == 2) return true;
		if(n <= 1 || n % 2 == 0 ) return false;
		
		long half = n/2;
		for(int i = 3 ; i <= half ; i++) {
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	public static long gcd(long a , long b) {
		long min = Math.min(a, b);
		for(long i = min ; i > 1 ; i--) {
			if(a % i == 0 && b % i == 0)
				return i;
		}
		return 1;
	}

	public static long findD(long e , long phi) {
		
		long i = phi + 1;
		long d = 0;
		while(true) {
			if(i % e == 0) {
				d = i / e;
				return d;
			}
			else
				d = i / e;

			i += phi;
			
			if(d > MAX_KEY) {
				return -1;
			}

		}
		
	}
	
	public static long findE(long e , long phi) {
		while(gcd(phi , e) != 1) {
			e = generateRandom(MIN_KEY, MAX_KEY);
		}
		return e;
	}
	
	
	public static long findE(long phi) {
		long e = generateRandom(MIN_KEY, MAX_KEY);
		while(gcd(phi , e) != 1) {
			e = generateRandom(MIN_KEY, MAX_KEY);
		}
		return e;
	}
}

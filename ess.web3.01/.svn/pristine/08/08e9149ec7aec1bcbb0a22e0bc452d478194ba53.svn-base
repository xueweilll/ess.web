package com.protocol;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernate.entity.Instrument;
import com.service.InstrumentService;
import com.service.LmuService;

public class MTAnalysis extends Analysis {

	public MTAnalysis(byte[] buff, int dtuId) {
		super(buff, dtuId);
		instrument = new Instrument();
	}

	private Instrument instrument;
	@Autowired
	private InstrumentService instrumentService;

	private String CVTId(int flag) {
		String str = "";
		str = Integer.toHexString(buff[flag] & 0xFF);
		return str;
	}

	private String CVT(int flag) {
		String str = "";
		byte[] b = new byte[2];
		b[0] = buff[flag];
		b[1] = buff[flag + 1];
		str = Convert.ArrByteToHexStr(b);
		str = String.valueOf(df.format(Integer.valueOf(str, 16) * 0.01));
		return str;
	}

	private String CVTI(int flag) {
		String str = "";
		byte[] b = new byte[2];
		b[0] = buff[flag];
		b[1] = buff[flag + 1];
		str = Convert.ArrByteToHexStr(b);
		str = String.valueOf(df.format(Integer.valueOf(str, 16) * 0.0002 * 80));
		return str;
	}

	private String CVTP(int flag) {
		String str = "";
		byte[] b = new byte[2];
		b[0] = buff[flag];
		b[1] = buff[flag + 1];
		if ((b[0] & 0x80) == 0x80) {
			str += "-";
			b[0] = (byte) (b[0] - 0x80);
		}
		str += Convert.ArrByteToHexStr(b);
		str = String
				.valueOf(df.format(Integer.valueOf(str, 16) * 1 * 80 * 0.2));
		return str;
	}

	private String CVTPf(int flag) {
		String str = "";
		byte[] b = new byte[2];
		b[0] = buff[flag];
		b[1] = buff[flag + 1];
		if ((b[0] & 0x80) == 0x80) {
			str += "-";
			b[0] = (byte) (b[0] - 0x80);
		}
		str += Convert.ArrByteToHexStr(b);
		str = String.valueOf(df.format(Integer.valueOf(str, 16) * 0.0001));
		return str;
	}

	private LmuService lmuService;

	private int Code2Id(int code) {
		try {
			// lmuId = LMU.SelectID(dtu.getId(), code);
			lmuId = lmuService.SelectId(dtuId, code);
		} catch (Exception e) {
			e.printStackTrace();
			lmuId = 0;
		}
		return lmuId;
	}

	@Override
	public void registers() throws Exception {
		int address = Integer.parseInt(CVTId(0));
		if (address < 8) {
			address = 1;
		} else {
			address = 4;
		}

		for (int i = 0; i < 3; i++) {
			instrument = new Instrument();
			instrument.setId(UUID.randomUUID().toString());
			instrument.setLmuId(Code2Id(address+1));
			instrument.setAddTime(cal);
			instrument.setI(CVTI(3+i*2));
			instrument.setU(CVT(15+i*2));
			instrument.setP(CVTP(49+i*8));
			instrument.setPf(CVTPf(51+i*8));
			instrumentService.InsertInstrument(instrument);
			// mtRegisters[0] = CVTId(0);
			// A,B,C三相电流
			// mtRegisters[1] = CVTI(3);
			// mtRegisters[2] = CVTI(5);
			// mtRegisters[3] = CVTI(7);

			// A,B,C三相电压
			// mtRegisters[4] = CVT(15);
			// mtRegisters[5] = CVT(17);
			// mtRegisters[6] = CVT(19);

			// A,B,C三相功率
			// mtRegisters[7] = CVTP(49);
			// mtRegisters[8] = CVTP(57);
			// mtRegisters[9] = CVTP(65);

			// A,B,C三相功率因数
			// mtRegisters[10] = CVTPf(51);
			// mtRegisters[11] = CVTPf(59);
			// mtRegisters[12] = CVTPf(67);
		}

	}

}

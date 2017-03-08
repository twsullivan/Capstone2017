package DNSQueryTool.DNSMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim Sullivan
 */
public class DNSMessage {
    private DNSMessageHeader header;
    private DNSMessageQuestion[] questions;
    private DNSMessageCommonResourceRecord[] answers;
    private DNSMessageCommonResourceRecord[] authorities;
    private DNSMessageCommonResourceRecord[] additionals;

    public DNSMessage()
    {
        header = new DNSMessageHeader();
    }
    
    public byte[] CreateRequest(int id, String domainName)
    {
        DNSMessageHeaderFlags flags = new DNSMessageHeaderFlags();
        
        flags.setQr(0);
        flags.setOpCode(DNSMessageHeaderFlagOpcodes.QUERY);
        flags.setAa(0);
        flags.setTc(0);
        flags.setRd(1);
        flags.setRa(0);
        flags.setrCode(DNSMessageHeaderFlagRCodes.NO_ERROR);
        
        header.setId(id);
        header.setFlags(flags);
        header.setQdCount(1);
        header.setArCount(0);
        header.setNsCount(0);
        header.setAnCount(0);
        
        questions = new DNSMessageQuestion[1];
        DNSMessageQuestion question = new DNSMessageQuestion(); 
        
        question.qname = domainName;
        question.qtype = DNSMessageRecordType.A;
        question.qclass = DNSMessageQuestionClass.IN;
        questions[0] = question;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        baos.write((header.getId() & 0xFF00) >> 8);  // High byte
        baos.write((header.getId() & 0x00FF));       // Low byte

        // Write Query Flags
        // Set to indicate a standard query with recursion enabled.
        baos.write((header.getFlags().toInteger() & 0xFF00) >> 8); //0x0100
        baos.write((header.getFlags().toInteger() & 0x00FF));

        // Question Count: Specifies the number of questions in the Question section of the message.
        baos.write((questions.length & 0xFF00) >> 8);
        baos.write((questions.length & 0x00FF));

        // Answer Record Count: Specifies the number of resource records in the Answer section of the message.
        baos.write((0x0000 & 0xFF00) >> 8);
        baos.write((0x0000 & 0x00FF));

        // Authority Record Count: Specifies the number of resource records in the Authority section of 
        // the message. ("NS" stands for "name server")
        baos.write((0x0000 & 0xFF00) >> 8);
        baos.write((0x0000 & 0x00FF));

        // Additional Record Count: Specifies the number of resource records in the Additional section of the message.
        baos.write((0x0000 & 0xFF00) >> 8);
        baos.write((0x0000 & 0x00FF));

        // Write question name
        String[] domainParts = questions[0].qname.split("\\.");

        for (int i = 0; i < domainParts.length; i++) {
            byte[] domainBytes;
            try {
                domainBytes = domainParts[i].getBytes("UTF-8");
                baos.write((byte) domainBytes.length & 0xFF);
                baos.write(domainBytes);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(DNSMessage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DNSMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // No more parts
        baos.write(0x00);

        // Question Type 0x01 = A (Host Request)
        baos.write((questions[0].qtype & 0xFF00) >> 8);
        baos.write((questions[0].qtype & 0x00FF));

        // Question Class 0x01 = IN (Internet)
        baos.write((questions[0].qclass & 0xFF00) >> 8);
        baos.write((questions[0].qclass & 0x00FF));

        return baos.toByteArray();

    }
    
    public void ParseResponse(byte[] buffer)
    {
        int i = 0;
        int pos = header.Parse(buffer, 0);
        
        questions = new DNSMessageQuestion[header.getQdCount()];
        for (i = 0; i < questions.length; i++)
        {
            DNSMessageQuestion question = new DNSMessageQuestion();
            pos = question.Parse(buffer, pos);
            questions[i] = question;
        }
        
        answers = new DNSMessageCommonResourceRecord[header.getAnCount()];
        for(i = 0; i < answers.length; i++)
        {
            DNSMessageCommonResourceRecord answer = new DNSMessageCommonResourceRecord();
            pos = answer.Parse(buffer, pos);
            answers[i] = answer;
        }
        
        authorities = new DNSMessageCommonResourceRecord[header.getNsCount()];
        for(i = 0; i < authorities.length; i++)
        {
            DNSMessageCommonResourceRecord authority = new DNSMessageCommonResourceRecord();
            pos = authority.Parse(buffer, pos);
            authorities[i] = authority;
        }
        
        additionals = new DNSMessageCommonResourceRecord[header.getArCount()];
        for(i = 0; i < additionals.length; i++)
        {
            DNSMessageCommonResourceRecord additional = new DNSMessageCommonResourceRecord();
            pos = additional.Parse(buffer, pos);
            additionals[i] = additional;
        }
    }
    
    /**
     * @return the header
     */
    public DNSMessageHeader getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(DNSMessageHeader header) {
        this.header = header;
    }

    /**
     * @return the questions
     */
    public DNSMessageQuestion[] getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(DNSMessageQuestion[] questions) {
        this.questions = questions;
    }

    /**
     * @return the answers
     */
    public DNSMessageCommonResourceRecord[] getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(DNSMessageCommonResourceRecord[] answers) {
        this.answers = answers;
    }

    /**
     * @return the authorities
     */
    public DNSMessageCommonResourceRecord[] getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities the authorities to set
     */
    public void setAuthorities(DNSMessageCommonResourceRecord[] authorities) {
        this.authorities = authorities;
    }

    /**
     * @return the additionals
     */
    public DNSMessageCommonResourceRecord[] getAdditionals() {
        return additionals;
    }

    /**
     * @param additionals the additionals to set
     */
    public void setAdditionals(DNSMessageCommonResourceRecord[] additionals) {
        this.additionals = additionals;
    }
}

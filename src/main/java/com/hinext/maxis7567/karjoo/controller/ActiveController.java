package com.hinext.maxis7567.karjoo.controller;

import com.hinext.maxis7567.karjoo.entity.Active;
import com.hinext.maxis7567.karjoo.entity.User;
import com.hinext.maxis7567.karjoo.outModels.ActiveResualt;
import com.hinext.maxis7567.karjoo.repository.IRepActive;

import com.hinext.maxis7567.karjoo.repository.IRepUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/v1/active")
public class ActiveController {
    private IRepActive iRepActive;
    private IRepUser iRepUser;

    public ActiveController(IRepActive iRepActive, IRepUser iRepUser) {
        this.iRepActive = iRepActive;
        this.iRepUser = iRepUser;
    }

    @GetMapping("/create/{number}")
    public Active createNumber(@PathVariable String number){
        if (iRepActive.existsByNumber(number)){
            return iRepActive.getByNumber(number);
        }else {
            Active active=new Active();
            active.setCode(ThreadLocalRandom.current().nextInt(1000, 9999 + 1));
            active.setNumber(number);
            active.setStatus(0);
            iRepActive.save(active);
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    iRepActive.delete(active);
                }
            },380000);
            return active;
        }

    }
    @GetMapping("/{code}/{number}")
    public ActiveResualt active(@PathVariable String code,@PathVariable String number){
        ActiveResualt activeResualt=new ActiveResualt();
        if (iRepActive.existsByNumber(number)){
            if (iRepActive.getByNumber(number).getCode()==Integer.valueOf(code)){
                if (iRepUser.existsByPhoneNumber(number)&&iRepUser.findByPhoneNumber(number).getStatus()!=2){
                    activeResualt.setError(null);
                    activeResualt.setMessage(null);
                    activeResualt.setStatus("1");
                    activeResualt.setTokenId(iRepUser.findByPhoneNumber(number).getTokenId());
                    return activeResualt;
                }else if (iRepUser.existsByPhoneNumber(number)){
                    activeResualt.setError(null);
                    activeResualt.setStatus("OK");
                    activeResualt.setMessage(null);
                    activeResualt.setStatus("2");
                    activeResualt.setTokenId(iRepUser.findByPhoneNumber(number).getTokenId());
                    return activeResualt;
                }else {
                    User user=new User();
                    user.setPhoneNumber(number);
                    SecureRandom random = new SecureRandom();
                    byte bytes[] = new byte[2000];
                    random.nextBytes(bytes);
                    String token = bytes.toString();
                    user.setTokenId(token);
                    user.setStatus(2);
                    iRepUser.save(user);
                    activeResualt.setError(null);
                    activeResualt.setStatus("OK");
                    activeResualt.setMessage(null);
                    activeResualt.setStatus("2");
                    activeResualt.setTokenId(iRepUser.findByPhoneNumber(number).getTokenId());
                    return activeResualt;
                }
            }else {
                activeResualt.setError("Wrong Code");
                activeResualt.setStatus("Denied");
                activeResualt.setMessage("کد اشتباه است");
                activeResualt.setStatus("3");
                activeResualt.setTokenId(null);
                return activeResualt;
            }
        }else {
            activeResualt.setError("Number Not Exist");
            activeResualt.setStatus("Denied");
            activeResualt.setMessage("زمان ارسال کد به اتمام رسید لطفا با ارسال مجدد ادهمه دهید");
            activeResualt.setStatus("4");
            activeResualt.setTokenId(null);
            return activeResualt;
        }
    }
}

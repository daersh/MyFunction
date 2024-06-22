package com.daersh.daersh_project;

import com.daersh.daersh_project.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReissueController {

    private final ReissueService reissueService;

    @Autowired
    public ReissueController(ReissueService reissueService) {
        this.reissueService = reissueService;
    }


    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest req, HttpServletResponse res){
        res = reissueService.reissue(req, res);

        if (res == null)
            return new ResponseEntity<>("refesh token null", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("new access token ",HttpStatus.OK);
    }

}

package homo.efficio.micro.todo.member.service;

import homo.efficio.micro.todo.member.domain.Member;
import homo.efficio.micro.todo.member.dto.MemberConverter;
import homo.efficio.micro.todo.member.dto.MemberDto;
import homo.efficio.micro.todo.member.etc.code.Status;
import homo.efficio.micro.todo.member.exception.MemberNotFoundException;
import homo.efficio.micro.todo.member.repository.MemberRepository;
import homo.efficio.micro.todo.member.util.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-13.
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    public MemberServiceImpl(
            MemberRepository repository,
            MemberConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public MemberDto save(MemberDto memberDto) {

        Member member = this.converter.getMemberFrom(memberDto);
        Member persistedMember = repository.save(member);

        return this.converter.getMemberDtoFrom(persistedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findByUserName(String userName) {

        Optional<Member> optMember = repository.findByUserName(userName);

        Member member = optMember.orElseThrow(MemberNotFoundException::new);

        return this.converter.getMemberDtoFrom(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findByEmail(String email) {

        Optional<Member> optMember = repository.findByEmail(email);

        Member member = optMember.orElseThrow(MemberNotFoundException::new);

        return this.converter.getMemberDtoFrom(member);
    }

    @Override
    public void delete(MemberDto memberDto) {

        Optional<Member> optMember = repository.findByEmail(memberDto.getEmail());

        Member member = optMember.orElseThrow(MemberNotFoundException::new);

        repository.delete(member);
    }

    @Override
    public List<MemberDto> findAll() {
        List<Member> all = repository.findAll();
        return ConverterUtils.getDtosFrom(all, this.converter::getMemberDtoFrom);
    }

    @Override
    public List<MemberDto> findAllBy(Status status) {

        List<Member> allByStatus = repository.findByStatus(status);
        return ConverterUtils.getDtosFrom(allByStatus, this.converter::getMemberDtoFrom);
    }

    private MemberConverter converter;

    private MemberRepository repository;
}

package cn.imut.ncee.dao;

import cn.imut.ncee.entity.pojo.UniversityInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UniversityDaoTest {

    @Autowired
    private UniversityDao universityDao;

    @Test
    public void insertUniversity() {
        UniversityInfo universityInfo = new UniversityInfo("包头师范学院", "NH","包头师范学院坐落于内蒙古自治区包头市，是集全日制研究生教育、本科、专科教育、高等职业技术教育、继续教育为一身，融职前培养与职后培训为一体的普通高等师范院校。", "内蒙古自治区");
        boolean isSuccess = universityDao.insertUniversity(universityInfo);
        System.out.println(isSuccess);
    }

    @Test
    public void selectAllUniversity() {
        List<UniversityInfo> universityInfos = universityDao.selectAllUniversity();
        universityInfos.forEach(System.out::println);
    }

    @Test
    public void selectUniversityById() {
        UniversityInfo universityInfo = universityDao.selectUniversityById("f43d0163757811ebb53a52540099a41f");
        System.out.println(universityInfo.getUniversityName() + "-----" + universityInfo.getUniversityCode());
    }

    @Test
    public void updateUniversity() {
        boolean isSuccess = universityDao.updateUniversity("e5512d1f7e4611eb873252540099a41f", "3A");
        System.out.println(isSuccess);
    }

    @Test
    public void selectUniversityIdForName() {
        String id = universityDao.selectIdForName("南京大学");
        System.out.println(id);
    }

    @Test
    public void deleteUniversityById() {
        boolean isSuccess = universityDao.deleteUniversityById("e5512d1f7e4611eb873252540099a41f");
        System.out.println(isSuccess);
    }
}
package com.music.util.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Words {

	private static String[] strChineseCharList = {
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"A������ą����������������X���߰����܇B���}�����l�Ȱ����t���@�c���\���������v���ɉa�Ȑ۰���訃v���ܑ��B��a�����}�L�P�o����^�������s�W��Ɏ�Y씰��Q�K�c�V폆�눈�",
			"A��������@���O�@�������I�����t�݋F��؁���B�����찺�n�����l���ꈁ���E�n�����Jໆ����ڝE�᪇�۰��H�T�������K֒�O�������q���b�����O�\ᮒU�C���S���R��W�S�İÓ�֓��",
			"B�ˁ��ͰȰǖ[�X�ɊBᱰŰ̆^�ư��μ��j�M��^��􃅩��i�z�����؞߰j�_����y���R�ɰѰаӏy�ֈ������E҆�F�ԉ�屙����װٰ۰ؖ���q���ڔ[�o���݆ܰh���޻���bٔ�ĮB��",
			"B������߰���Ά��n������L��歚�ӻ{���k�Z������犔�E����t�O�[��k���L����ʰ��ߙ�����R���D�󽉰��g���������Y�����M����Ő�r������߰����������}����_",
			"B���������A����𱫒����~�ጇ�����h�R���dم˝�����d����������ǘ���s�b���̙���ޱ����t�`�鱰�����G���d����ˍ�l�ձ��m����ؐ�����K���p�����p��㣪N�����˗f�D�f��F��",
			"B���K�����նF��͓�����R�c݅�^�v�L���ͱ��y�ڂ��`�G����Q�����M�Η��ВٗL����ݙ�ȱ����a���ԾX�����E�e잱ñŉl��G���a�ȱ��P���S�s��ݩ�ǋ�ذ�ȉ��a�������a�H�˖���",
			"B�±ʻ����P��؄���ұرϱՁ؈f��םߛ���Pn�ٱѫ��R��ݱ�����Ћ����ر֗a���]�[�]�㏌����㹜��z�����v�C�P�􏒗���������b��ɜ͚�����sűןΪ�����a�����E�Ɲ���",
			"B�X�K����ԏY���`�o޵�v���I��娱�ۋ�`����{�V���g�@�S�{�K�L�x�F�E��z���������ֱ��ԮK��������߄�Q��߅���b�c�e�ױ����Ґ��ܷH�ۼD�u�x������\�M����ኍO����c",
			"BҌ�O�±��gŌ�l�����p׃�����ԪYà�w�������˟ϱ�����쮃��[�d�g�Aٙ�S���j�k�l�n�s�����Օ�F�l�~�l���B����M��̋���e�����a�r���X�h����T��ߓ�����ϱ�ñ����Ĭ��e",
			"B�f�ك����I�M̞�ٞl�E�_ϙ�\�������P󝚛Ĝ�����x�W��������ޒ�䉙������v�T�m���u�m���������s��������V�]���K��Ո����v�ⲡ�p����}���@�m�h�h���������C�B��",
			"B��ࣼ��������\�`���G�@��Q�����ò����������N���`�R�񒩛¶z�ಬ������ȕ������K�A���k��������Ň�C�g���c�D����N�ŵR�~�n�o�P�ݙ��q���}�˹����X��޼\�L�Y�N�m����",
			"B������c�J�K����߲�������a�G�Q�����Ѳ������h�i�Єψ�������곏Eɞ�Y�^�X����������",
			"C�����g�n�²ŲĲ�ؔ��òɂ��Ɗ�u�ʒ���P�Z�Ȳ˗��̿n�k���{��۲��вϲњ��M�]�L�Q�T�ґK�k�����o���Ӄ����L�N負|�ց����ײԂ}�Ղ���Ȝ檁�n�Pœ�@���ؙ��ى�ٲ�",
			"C�ڕ����Ѝ���ɘ���G����[ܳ�H�ݐ��x�H�_���������R���Y�Ŝy���m�ZɃ�x�������~ᯗq����Ӹ}�䳀���K�e�u���O��P�aӐ����������ő����l����˲�荿����x鶲����",
			"C�����d�g������汲�p�����O��٭��}�򆶃��Ѓ��ϊ���{��{�����i��濲���������ȟ��������S�a�ܝ����C�v������e����������ʎf���p�s���K�B׋����i�C�P���}�Ʈa�b",
			"C�����ۄ��ݝI���r�~鈺o���A�p�P�U�Ϟ�׀�㳃���ϲ������]����潜C�����ѕ��Ǭd�m��_���K�������ɳ�������D�O�^�c�L�Ϭ��q䖃����^���L�l������㮳��Y���S����`�����˳�",
			"C�������k�o�����������̳��n�����˳��z���}�R�����J�V�C�����|�����l�����e��܇���q�p��͒������������ފ�E�s�����J�س�����u���ӳ������Հ�o������������Ɛ巟G�k�����H��",
			"C�Z���c�m������I�R�l�����mڒ���׉}���~�{ٕ���įM������鴷Q�Y�Z�����r׏�p���ߠ����Ղ��������b�W�r���l�γſB����X�d�f�V���|�d�p��ة�ɳʳ��ǳ��J�ǌk�w���^�������A��",
			"C��Ô���͗����̹f������S�˳��\����䅝��γșr���j���r���ѳҎ��G�ӳԊw�o���ꏫ��E�����W���ʓ��ղl��|�A�V���c�~�J���[�ڳس۳ٍI�ݳָ��K�F�M�P�W�Yܯ�����s�߅�",
			"C���L�ޅq�݈��n�u���N���I�n�l�r���X��߳��ó����x�Ȑp������뷟U�bജ��ѯv�S�M놑y�@�o�J��B�둴چ�����u�����_���ҫ����������o��n�Z��ی��獃꙳���|���N�ߠ��",
			"Cٱ����㰳�Ǔ��[��{�����O����l����b�Ů��Pׇ׉��E�����G���h���{���c�����������،�iۻ�������Z�a���Z�a�e���n���ˏN�X���ÎЙ��{�������R�X�ƴ��ƴ��������ҝ郦�s�l",
			"C�A�s��ء�I���a��}��X�e�G�I��`�s�����U�z�����[�ߚbĕ���|�����u��������ݴ�밴��������������״��N��ݎ��F���b�����[���A�i�V�Ѵ��������������R�����l���}�J����k�y",
			"C���������������ﴷǔ�鳴��N�q�I�~���N�@�a�������t����Ꙛ�j����ꁴ���ݻ��Ë�Ɯ��O�ȝ_���c�����o�òQك�w���ִ��u�Ɛ������O��Q�b�z���󴇚f���p�q�ګu���e���ʫy����",
			"C����Ĵ��~�e�ȮN�ǴŴ������i��@��B�y�Q�o�\�]�ˁհr�c�΁�̄p��Ƙ��Ǆ�a�y��Έ�n��Ӵ҇��ʏ���Ɖ�S���t�Н^�[�����ڭB��ϲj�b�Z�S�W�^�ԏ���Q���������F�p�z",
			"C�{���q�����ߠ�ց�՜�����ݏ���c�����������⧋{�|���K�u���ׯ��ؿq���y���A���ߥ��ڔx�f艔e���m�ܟ�ۚ��x�Z��޴߃����N���������J�t�y議����Q���y������㲴���륟n",
			"C����ċă�W�Pěߗ���坴����Y���v�꬛�u������i�q̑�ύ������c���z����v�z�ȉ���s�u���H����x���S��ɲ�����������",
			"DԌ�b��֝�b�B�k�h�d�L�_�n���κ@���T�q�������Ǯ�����ׇ}������򈙞��e�Q�A�δ��z�Q�����R���[�Jσ�J�N������󁇱o�\���y�����������a����᷎�߰��ʴ��������������܍ܤ",
			"D���H�����D�x���οD�\�l��ŕ�캉���O�y�^���l������N�n�������l�[�����S��э���F�ل隗�R���[���m�����y�㼍���d���^đ�������X�������Dࢆ��������̵������E͞�g���Q�n�",
			"D����壶V��}�X�Q��ٜ�K�������ɹY���}���ǭc�d��ř�}�����ԓ��[��ו���ʈW����������ЮG담X�^ځ���n�T�U�D��߶������s����ҵ���ꉵ��u�������v�I��옘���Z���W�F���\",
			"D�������K�ͱI���R�����m�|�p�R܄����z�Û�����ｇN�Ե��u�ēg�O�Y�Ƶ��O�⋿���O�~��Ř�R�ŵ�ꭵ˵����Q���ص����뙞狁��͏�Д������ꝵ��h�ֵ��ᴔ�L�C�f���J���m�φv�е�",
			"Dݶ�b�����{����H�L�E􆔳��ˋ�p�e�Mص�}ڮۡ�s����׏~�����ƒ�ǜ�B���W�صܕA�m�Z�K���d�ۈ�淵�ޞ���\�b������ǅ���K����޵كC�E�V�d�V�������O�d�S���R�E�ǔ����م���",
			"D�ᘕ����ۆ���o�p���d����H��L���ϵ��_�s�ڵ��������������������U��͟늉|����յ崐�������a�@��P�����q�h����f���M���y�����m�ŏt��������ӎ�����",
			"D�y�u�H덯��L��S�����B�|����U�g���]�y�W���ܦ���鮒�x���e��������P�޵��HсŎ����������A�B�ۇö��궣����k�۶��������w��픶����Y疶��G��ӆ�}��b�����ֶ��O",
			"D�V���r�G�����M�A������ᴖ|�{�k뱂��Ċ�����Ǉ���X�[���C���H���ٶ���ʐՉ�������튟ἶ���������ޓ�����؄Ӎ��ϗ��L�P�뚄r�t���������X��ŏ���c�h������^���K���W��",
			"D��Y�uÖ�H���k��Z�L���L�]�^�`�aྲྀ��`���������빶��t�i�}���L�E�X��^�������٭{���y���x�K�G�~���o�b��tט�����ƶ��o�Ķ��Gـ�Vܶ�ʶŶǊ����T�����|��Ζ����C��",
			"D�e���H�̶ζωF��Ș��Ѭ��a�V�;�����呔��Y�f�����щ[�ůy�X��玶ӶԶҌ��q����Խ������A�c�摻�}�B��m�֐��ضՉ��ݓ檖���Ǡ�����H������O����������ޚ�۶ٶ��g�D�]�q",
			"D���v���Ͷ߄������և����섋�ޔ������k⇊Z�A���y�I��\��⒖����綗��r�o��E���D���m�����w𙈑���������F�G��������z�����������ؼ",
			"E��j�����������ވ�����k��ݭ��Ӟ�x���eﰶ��d�M�P���~�Z�[�F���S��F�E��x���i�������q�ٳX��@�эS����b�L�����v���r��ܗ���Ո�㵜����`�Q�]������c��i�Y����ʂ",
			"E�{�O��Iج�A�F�@�_����d��t��׆�y�|�{�C����W���E���s��������z�X���H���s�L𹻕�[���X��b���W�����Ƕ����ꚾ�����D��n߃ڍ���p�r٦�n�����@�^�EԠ������",
			"F�T�����v�D�C���S���p�����^�H�\�}Г�ܖ��U�x���o�k�l�����e�������үV������뷤�X�P�y�U�t�ᷨ���z�����m󌷫�鷬��h����ᦑ���������N�c�x�Y���F�G�K�i�����o���t���w",
			"F�B�C�����u��ެ���ܭ[�X���T�����잒�\���xϛ�������B�x���ﷸ�i�����������ӌ܏��F�D؜�G�J�˹��ў~���������ʠ��՜E�K���[�p�h���������������·÷ĕP�X���f���뼏��",
			"F�L��J�ŷ������w�Ȋ���d糷���U�q�q�p�������[�I�W�a�E�y���Ǖ����N�n�˷̊O�쳗����ʄ�u���ͷϖ{�����Εh�тn�|���X���Q���M���ЏUʆ�հC���O����]�ַԎ��׷ҕS�ոj��",
			"F����ӟ�m���p냖B�i���؊}��ږ����R�r�i�_�`�����k�B�V����ʈ���X�J�����k�������r�M�M�O�R�۲b�v�݈e�k�ܷ޶l�Ƿ߷��f���^ď�S����a��灧�K�N�~��h�O����`���L�o�傪",
			"F�Q��S���t���h�׷���ȷ䯂�^�t�A���h�l�S��Qۺ�����b�p�K�뒸�������ł�p��҃���S���gٺ���u���R�L�P�iو�X��҅���u������������]��ߑ߻�����c���N���Q�C�u���X��",
			"F�����K�������a�[���������J�A�F�ʁT������i��_�ڷ�ܽ�]����@����������������ƅ�������������ޫs�t�w�����ۮ��I�b�󸡮}��ݳ��������J�M���A���E������̒�����n�Jȃ",
			"F������ŀ�ݷ��R�V�O�D�_�J���}�hᥝ�����q�D�~ݗ�H���v���f���������}�Ը��Y�M����ᜒѸ��ӟr�G���港�o���븸�������������}�c���⸴�yӇؓ���bЕ���帱��D�k���c�����i�|",
			"F҄�c���ڸ����ֶOч�羔ʍ�l���x�`ݕ�Vَ��������v�g���",
			"G�q�r�W�٤�Q���ٸ¸��m�ثV���p�����򊡍Y�D�|�஄��ԓ�d�W�^��Ľi�Yؤ�_�����q�ƸǸ�ȑ�}ꮸ��w�����[�y�ɸ��Q�|�˫\�������ո̸��������x�Q���Ϲm�l�����v�����r�ϸҹC��",
			"G�����s��ߦ�����h꺱Y��礂����ƽC�l�_�֙g�Ӟ�������ոڸٸ،����I�׸ք�Ւ��G�����V����s���۸ܟ��������޸�߰w�{��غ�����R�ݸ��p���z�k�������ǐ�������ª����",
			"G�c޻���̸��ھ۬�z����ﯹl�J�a䆸��������������˸��鏐�����x�m�w�R�g��抅ρ�������ت���Z�����ܪ���k롘����w���k���Y�s���u�P�R�Z����������򴂀�Ѹ��w����",
			"G������ب��ݢ�^�j���j���u�ʸ����@�ԟ��c���f���K�s�����Q�칡�y��箹��c�������i�����톯�ֆ񹤹��������r���k�Ź��m��򼹪���@�b�����p�C�b��y���������������\얹���ؕ�E",
			"G�˃��Ź�������Н���h��ѐ�������xḹ����۫v�T�V���U�x�x��ڸ������ƙ�ڹ����_��찓k���g������Mُ�����ù¹��}����f�g�B𳸚�������u���L�M����챹������H�O�Y��ݞ������",
			"Gڬ�ȹɍg������l�E�܂Ù���v���병�b𠘀�����Řb���Y�M�[�J���k�k�W̹ʃ�ˈ؍�����������������흃l�d���AϹ�Ƃ���O�����N�����T���m�օ��ɹЄ������Ԉqڴ��",
			"G���G�L��ԟ�ԹՖ��ʹy�֐s�ع۹ٹ�ҋ�Ĺ��F�A���H�Q���^�b�ݯp�`��݄�o�]�^�I�A���ᛌ�������ʐ����T���k������暹ࠃ���e�ٹ���}�➻��Ӟ՞��ۈ���ƚ��D���׃Z�_��U����",
			"G����U��ѹ��棹��ߞ�Ɨ�뎢���w���O���˹��F���|�ً������Y�n�h����峹��с��й��{����܉��뵃�Q�М�͊�mԎ�����j���۔����������ιK�F�}��Z�����ʘ��W�l�����i��",
			"G�Ж����ɀ�L�F��݁���P�������O�e֏�������������u�������H����偏������������⎽�I���X�b���������Ǒ���x���[����R�J������ݸ��",
			"H�g�W�h�m�s�D������́��@�w�唐���U�N�o�V怕��璚�򅚐�y��ԍ�X����ⒿS�]�H�o���q���^�W�i�N�s�@�{�^���R���˺������ܟQ�h�V��������������񛇯���a�ˁ����c������E",
			"H���H�A�����������T�H�i���Λ����ϗc���ʺ������]�b���K���w�n�{���Ⱥ��G�J�_����I���\���������~�۪R�t�����H���F�\�y����͔����L���I�d������Α�h�u�A�n��[��f�㺼���",
			"H�����a��ؘ�V�W�@������޶�h�����ƪ|�s���_�����غ���婻Dϖ�q�úº�껕a�S�h�ƺĕ��B����|̖�������������A�����������ڭ�Ǻ��X��Ϛ�̺Ϻ������͊��ӍP���A�t�҆Y��",
			"H�˱B���Ɇ��Ԝz�ж����M�������Z��Ԇ�F�������u�i�����K�����H�[҇�����Y���ވ��،y�Z���R���E�ֺպ��G�ְF�e�Q�L�e�f�S�g�\�ںٝ��ϒ���앁�ܺ�ԋ�޺�ߐ���Æ���a�����",
			"H���t�a��M�����C޿�U���ցY�b����������ܟ�p��ްݓ����Z����k��ƺ�K�Y���Ȍf���a�y������fݦ��ļ����o�{���A��|�s��ȇȈ�v�b�����D�~�p��C�f��ޮ�������Z�{��",
			"Hڧӏ�Н����U��@�펫��ȉ���T�����F���A�f�\�����C���ᛕ�˺��jܩ�_�����`�c���I��~���U���~�~�����O��㱜X̏ܠ댇F�����i�X��_�����@���������W���؋|��⩽`������",
			"H���P�S���ι������k���eΙ���g��L�b���E�{���C�K�[�R��䰻��L��̕�}�t�U���u���������ꎏ���Z�������O�����˻�����𭽜�����������d�����U�n����o���_��s�I���_���N�ɳ�",
			"H�A�j�k�f���������������W��LΔ�f�����������������O���î�Ԓ�������勽��Ֆՠ�X�E�s��������ё�ב�ќ�љ��j�x�������|���Z�b�ȑ�ⵚg؎ג�O�����`��B���P�Ȉ�~�b�f�v�}",
			"H���a��٭h�o�D�q�]�Q�S�X�߱������k��ۼ�K�J�»���佻��𻼗h���Ն����ѓQ�o�����~���񯈘���ߧ�Ȳoː���Z�d�x�����Y�E�Żʂ������S�Ɔň��h�������ȏ�ؘR�ͬ����ꪍ�B���",
			"H��Ŋ���Ƿk�W�����c��ڇ�u�b�m�U���U�О�r�Εs�N�ѻϐ�ԅ�m�e�����Ɯ�n�Ͱ��w�Ҟ�ڶ�Ի֒��ӛ���͟F���Y��^�]�����D����q��Ԝ���N�j�v�E�x����Ğ`���߻؇�ݏh�j�o",
			"H���ޒ�C�D�`���z͠�t��Κ���m�S�e�܌����x��䫻��U�������{�����޻�๻ݽ}�����_�R�������ٜ�Ԑ�V�a�G�]�d�H���ۑ}������ޥ�ڙB���_�C�D�M�_�b�u�Z�_˙�d���β~�x�D�",
			"H���L�P�M�T��u�w�e�����ԐǗ��E�J钏����Ɯ��꿌�@ڻ�[�������k����o՟���������x��߫�d�����ߘ���ⷛ[������i���n�񄊻�؛��������؊_�C�@�����f������m",
			"H�h޽�ɕ��G�\���Z��������",
			"J�n�p���k�u��җ�ȁ��e�z�r̎Ց��υ�]Ɩ��v�\���W�����E�S�Q�Q�Z�o���t�Jآ�����Wߴ�����Z�����ἡܸ�������Ҽ�����������|�������������U����ކ�����K�_��ょ\�Ę���",
			"J�ܻ��}�u��ԑ��C���^�e�Z�Y���s���ي���f�u�I����^�^�Q�Z�a�V�a�~�W҈�i�W�b�A҉��������᧏��������ؽ٥�B�u�������E�u�ż��V���f�h��Ð�C꫼��O�꜖���J��������a",
			"J�l�����c�l㚎N��񤹜�mު��n�W�vΎ݋�Qۈ�ŝ���U�g�P�W�n�|�}�����M�j�䛋򱼷��������������ؔD�m�����ƼǼ��͈j�˼ɼ����a�ʼ����∅�c��䩼üoƈӋ��������ӛ�ʼż�",
			"J�¼��m�����Uȗ�ίs�@���_�E�H���ߝP�T�I�I�b�H�����ݕ��Ղ�꼽���̷]�E�H�ٙo���Y�J�J��۔�H�T�^�հU�_�R�������^�n�D�z�V���C�q�K�ӼВz�ћv�ȼϚ�������k���_�ªo��",
			"J�ʠ��`�e���ؼO�j؆����]�S�ۣ���P���v�����̼�͐�O��e�]�a���G�׫w�Δϼּ؋T�З��Z⛘\�����x�ۼݼܼټގ��k���{��ꧼ��Լ�߼����芦������Ԑ��\�����G�ՠ���",
			"J��Ȃȅ�g���߼�{�R����{�ٟҾ}�zʗ���ϟ�]�V��D�K�J���h�����M�V�p�����W�[����̂�d����ż���낛�����ȼ����윗���ʜp�������d﵏������ُ���꯼��z�u�M�O���",
			"J�����Oֈ�C�r�x��ϕ���{�v�}�|��������������������k�������������ɽ����V�{���립����[���`�����Ԙc�����������GՐ�v�{�`�݄����Z�]�I�T�{�����M�G�W���SŞ�Y�a�b�{能�",
			"J���������{���w�����{΅�����K�^���Q�����佮�P�\�F�������׽��X�\�Y�������v�vG����x�t���筏��H�{�������@����T�n���u��ܴ֘�L����毽��j�����������������Ն�̗�ދ�",
			"J���B�x�z��ĉ���p�o�Ժ��t��������T��ٮ�ؽƽʽȕw���ýŽ½��R�˄�븟��_�]�����]�q������蔺���ɕݭd�C���a���q�����R�ЅӒ�ӊ���νϔ��̽ќ���U�ҝ]���݋Ъ�ˊڊ�I",
			"J���_��ᆽ��ܽԽӒ��^���A�ൈ�m��Ý�֟��M��f���X���݌���ڦ�f�g�ٌ�M�o�½��wڵ�׽��ޗ��A�}ӓ漍����K�ܽY�˘P��޹�͝ԑ�]�ؘm�ٽ�ɕ�ڝ����m�d�юY�O�@�O�^��",
			"JϝϘ�V�㚲�d��w�N��������ý��d��v��V�|����Ȉ����p͎�����]ћ�������ă���Ꮍ���\���b�������Q���n��\�v�T����ᎄ����Ǟ�H�������۝W�M�oɓ������\",
			"J֔�~������桽����B���ś�ݣ�x�������ᵉ�M���ƌ��|�ý������q�B�����Nف��������|�a�n�P�B���i�����������p���G����캔�S�Uݼ�����澦���������Y���X�L�~�����@����",
			"J�S���ٌc�G�H�q�������ӎ�㽭E���ǠG�Z�`�iς���n�����ɛ��փ􏆏�����ޟ��溗J�}�Q���x���d��������e���K�߾��o�s�R�����Ոs�爷�N�o��S�ׇ��ĂC��ޛ�ӟK�����G���T��",
			"J�❰�Eѕ�W�L����`���j�m�����\౾��[���b�žÁX�`�e�C�w�ľ��i�¼����N탅E�ɾʾ̾��і͂w��ǾȾ͎��B���֏G�H�W���f���J���Y�n����H�]���ӾЛt���ھԂ����үY�Dꏊۊ�",
			"J�J����x�����K�����ŉ�AՇ�g�|�x�~�������֛��I��ۚ��H�h���`���Ɯ����]���v��ٙh��V۞�q�e�^�G�M�׾ھپ��쒤���_���X���e��H�Ι��z���ڪ�������ܛ���ߚ�ߒ��Z",
			"J�j�涀�Ҿ��ƃ�绉�`�iЍ���Ͼ���n���q����̘�e��������u��������X����������䏌��Z���M���֠��������Z����g����N��þ�ێ���ǚ��Ė䟊F����������������C��۲��",
			"J�v�I�C�g�^�C�\�پ�ތ֌؁|�H�޾��]�ܾ����`�i�k����O���D������Ⱦ������ҏ���E�bڑ�ʽ^�~ҙ�k�f�㬜�܎@�D���ؠ�Ɐ�ʅާ�_�`���ә@�Q��Ĕ���p�q�u�H���B�����X珞���Ӂ",
			"J���ݾ�P���겟��؏葾������JЂ܊���q�S�T����x�A�ް���ҟ�z�������z�Ϳ����}�����ܕ���𞿥�B�������D�y�����K���E�Q�R�U��Ю��",
			"K�H�̞G�a�R�ˇi�s�F�d�l���X˛�Ͻ\���j�O�q�V�vĄ�w���x�s�^��Ў�J�m���A�h�y���ҭg�Q���t�O�t�������א�Ä�V�i�ǿ������������Q�l���K���_�瘿��������]���P�����܉N��",
			"K�a�����|�z�G�a��͙����ц��b�f���ݿ��述����ݿ�٩��ݨ�����d�|���R���b���{��|�����T�~�����ܿ��o���^���{�_�K���������Hߒ�㿹���ʿ�����`���}���������࿾�����D��",
			"K���w���\����� �����m����ڐ�ݍ��ïz�d�V�t�����⎘}�P�Ř�����W�f�w���L�ǿ����P��᳞ܜf�����ʿ˿̄w�Ą˿͍Q㡊Č��Έ�����ྐ���ﾴR�~�n䘴��S�G���\�ѿҿ��c",
			"K���o�������y���Կӳn��דּ��U�L��H�|�g�{���]���ň��ǐ������w�I�y�׿ֿ�읏W��������g�ڄ�ߵ�ېD����t���@���؝A�fޢ�]�p�d��ߠ�ݿޖ�ܥ����ڜ�����p�@������V竎�s",
			"K�\�F෽f�㯉��ѝ���䊯�~٨�E��㒿����g����w�S�Q����ۦ�����ډK�����~���X��Ē���[�d�팈�����w�y�p��T�U���ڲߝ�N�ѐb����n�E�H���ڿܒܜ�N�\�Ń��������������D",
			"K�r��p�ܿ�q�m���T�_�L�A�k�q�����Y�Ǖ�p���V���k�k���l��㦿����Q̝�N��l���u���k�ظ�����Y���җ��������`���w��K�j���ẁ�i�t�������ۓ���њCఋ�����������T",
			"K�]�ő|����ʉ�暕�r���A�q�^�������҈܋G�����^Ǎ�T�j����h����d�����Jт��@���K���C�O�H�{�A����͉חy�������X�ڶ��������ٱ�����������I�Q�u�v�������S�N��A",
			"K�p���H�T�U�i",
			"L���л��v㉌��ňh�ԛ�~�w�fƌ�x�k�t�}�T���B�k�F�������G�����n�����ͮo���M����������ǉ����ǓX�r��ˈ���ݜ����Y�������`�h���|ė�j�m�D�F�ʭ��J�_Ϟ�n�B����g�|�������",
			"L�[�@����Ɨ��Z�[�R�F���[�X調s�n�Q�D���H�������A���l��ه�m�s��`�|�����D�]�s�[�����������ȟ���������܃�����L�{�[���@�s�h�E�Ӕr�����@�a�m�̙ڵf�w�۞��_��׎�_Ҁ�|",
			"L��e�������������Y�����E�G���Ћ��[�G���젊�|���ĠA���E�f���������hॄ����O�D��ݹ���ȗO���v�Ƭ�����ﶹ^Ņ͙�H���q�Z��@�����ϖJ�R�iɇ���L遖T�������}�̻����̈́���",
			"L�J���[�����Є���暑X�����U�A�����o��������ІK�ѐ`�N�᫙�z��͌��ʘ��u�L���ӆ����ҋ������Q�g�~���b��߷���A���I�W��i�՘S퉺{������E������ɠ�î��ۿw�ؙ��z���W",
			"L�n���r�D�[���h�Y̅�m���}�F����ڳ�݉C�t���C���u�����܉��Nˉ�����X��Ϝ�{�|����P���ᛤ����I������L�[���G�b���K��h�q�a��Úܨ����G���k�J�䂒��㶱��o�^��{�������V",
			"L���ꐓ�~��Ǘଗ���𿄘��@�]��Ń�����ˌV�����L�{�H�kџ�������r���[ւ�r��޼߆�x���\�P�v��Ξ��y��G�C�c���g���h�P�~�Z������ٵ�b��沍q�������e﮻��Y�N���墶Y��",
			"L�~����ߊ�k���������������^������߿�����������������n����ٳ�����߳Pƍ���\��۪�ڐ����ۖ�����������wݰঋK���P�������j������������́������W�������T�W�W�E�������s��",
			"L�F�G��њv���_�t�_���W�O���`󜃢�噪�i�ض]ϋ���Ȕi�r�|�Z˞���s�����X�[�cϠ���[�`�b�����]��ׁ�^�����Z�u�c�����z���������������B�����́������X�iɏ�U�YҜ���V�t�z�n",
			"Lў��奝￀�I�O΋���ۚ֋�`������H����^�d�������ϓ����I���Ք��aĘ���c��������������t���j���I鬟����򾚝��b���朞��n���~�|���Z��������c����ܮ�H�Ԙ�݈�Z",
			"L�I���Ɇ|����Þ�o�n�W���u���]���������������gՏ�v�y�G���������ȍ��Αl�k�ڋ�弍������Ô�����|���ǭV�NĂ�X���ӌ׏\���i�Iْێ�r��s�m�������ޤ�v�R���͞��ό��̲t�֚�",
			"L�����������Ê��h���Ɣޘ�����ҟI���Ԫd�}�џ����~��{�V���h���Y�C�����Q���v�������������ܕ��ջ��r�@�������O�ם������ݕɟ��U�������R���L���O�ɞ��l������[�H�z�A�j",
			"L�݄C�ԏ[�ޑ����ř_�ݰR�S�C�ߐt���ޟi�U����V�銮��A�C���\�k�`����c����{�犖��H�������ߕ`�E�������s�g�{�n�����_�ꍒ�����R�O���@��������z�����e���fښ�CɈ�k�",
			"L�q����c�C�s��|��ʙ���C���o�w�U���h��۹�N���h�g����`���������tЇ���I�X�����ʞ��������f�q������������^������]�s���������v�������e����m��]���H�@�Y񜇮",
			"L�˞g˘���y�s���B�d�s�t�i���V����ΗB���P�ﳾ^���S䍙P�[�������g�A���z��ۉ�C�w�f�j�w�F�M�����������וo�����ʱ�������¡���𘙝V����\���a�b�d�X�N��V�ɠz�����a�b�t",
			"L�\�@�F�H���L�[�x�_�T¤¢��£���]�ŉƔn�_�U�Y�����L¦�͊�ග���E¥�D�I�f�V�s�ǟ������k�eŔ�N֌�}���V�t��§�v�ⓧ�U�R¨�tª��©���ί����U¶��ߣ���]¬®«�䖛��¯��",
			"L�����y�S��­������R�����]�m�o�G�o�J���t���F���V�r���A�B�_�z�B�z�|�R�u±²����°�u��³̔�o�Fɝ�ĝ������ִ{��Z����ś��@��u�P�f½��v��¼�V��¸��ꑊ�G�O�˳tǊ",
			"L��¹���f��»�J�����˱J�Fµ���T·�n�L���������I¾�̟��r�y�tڀ�j��Xº�cʀ��h�j����F���V��ۍ�A�J�غ����G�I�c�n´�e�UӀ́���¿�����e�|��˃���y�H�������L�H�×o",
			"L�f�~�����Ž��ƌ����o���X���@���t�|�������Ɇ`�����̍���Ȅ�G�v�]���������l�r����������������F�J�a�D�\�n����螤�c�L�K��̉莰f�g�[����ᛁy���Ԯ�ﲈG�s�x�Ւ���������",
			"L�ځ��ւ�ꍇ��E�������Sǒ���@�K�]�F�i݆����M���b�ۈ���Փ���b���ކ����������T�����������_�T栃��Z�M�}߉�����j���s���`�z���s����I�{��e���R����������J�j��",
			"L���������i�B�z�w",
			"M�]��ƃ���N���O�������b߼���o�m�C؀�ќ������~�ӠӅ������`���苌������q���h����W�����������R�Ԫw�j���aΛ��i���}迵l�U����زK�O�q�M�R�T�K������������ݤ�I�X�{��",
			"M۽���ω��������}���]���u�~�@�A�������`���������m���z���M�N���U�̜������M���Z�\枲����Káܬ����������A���ם��ܿz�T֙�N�p������æ��â�n�xä���}��ã�W���������I",
			"M蚯g�{���MçǃƟ������ϑ��è؈ëì����é츜~ܚ�F��ê�������^�����î���������í�Fɋ�T�аp�d��ïð�gó���ґ�uñ�Q�|�ؕ�������ò���c���x�ۇ��Q��ô�Z�]ûöõƀ",
			"M��ü�zݮ÷�CÊ�d��ý���؜�⭱��B鹘Mú�s�C�P�r��ø�����Yù���[���u�j�B�|�q��ÿ������伋Z������þ�ʠB�z�V�e�Ò{�i���|�Ǳt���¯cڛ�m���S�ȹ��n�������ѫf���T�Y��ǖ�J",
			"M�`�{̊���ː����F�����ǂ����Bå�m����E���_������������ޫ�p�{�����̑����B�����ʚٲ��������s���X����L���ͮH���������i����Q�����Ή����D�_��[������������oԙ�i��",
			"M�C���������ҫJ�����۔}�����J����d�������ͻ����М}Ȏ�^�U㝌B����������ٍa�z�s���ܜPҒғ���׉Q��Қ�טa�D�e���Z������Ǝȝ��z�k�����]�P�ߊ����i�޾d���X�e���",
			"M�������D���������a���������҆��������x�ﾒ�|�r��E���@�M�I����b�����ŋ����]����������翺F�����������k���R����_�P��}�����I�p�ϑ̞f������`�f�x���񈄊�ẐB�F�G",
			"M������a���F���ϬY�\�ŕ��z�x�C⌾r����F���b�������ǔ����������I���h�����w�}�o�O��������ϟ�����������b��ڤ�L�����p����u�q�Ԙi��Q����K�⊱���D������Ԛ���ѿ�և",
			"M������������ġģĤ�N��Ħ����ĥ�Uփք�V�x��Ģ�rħ���Ĩ���Oĩ���\���\�{��ĭ��İ���b���t�u���]���eĪ�����Q�J͈�{���sįĮ�����ī�����a�h����a�}Ĭ��ˏ�_柠j�c�g",
			"M��i��Ĳٰ���c����ı�w�\�����Eĳĸ뤪���ĶĵķĴ�\���r�y�k�����c���a�[ľ��Ŀ�L���������ٚ�ǀ�]��ļ�r�ĹĻ���H�����fĽĺō����J��C����",
			"N���j�ֶg�_�D���ÆH�����Y�x�T�Ë�ڙƛ����u�~���o�A���S��ؿ�Ғ����y��擁p���~���ǅȊ{���������Ƽ{М�น�vܘ�y���i����G�ÌY����ܵ�ʯG���iޕ��ᝋ�����������ؾє",
			"NΗ�r���Вo�����O�ϊɮ~�~����a��骟��Q�y���D���l����ڋR��e�����T�Ι�����߭�����Qث�`�ΐF�Ӎp��������i�L�H���t�j�L���Ր��ԅD×�ߐ�����X�Z�֋C���m�[ګ�ű��G��",
			"N���F�H�����ߟ�����ǂ��G������������u�ߌɶv�\��Њ��N��e���C��U�r؃�����F���u�M��٣����v���s��컕��D���W�M�o���X��ދ���f����[�z�Ӑ����������啿�Qā������j�P",
			"N�|���R���D��T����������݂�v�f�T�`إ���������|����������U�\��љ��������I���[�}��������������꟔�࿍��W���f�h�����L�Q�Y�R�����E�Z���ǻH���m�q�b�f�A��ב�b�",
			"N�DÀ����������š�������|�������������ޔQ���f���_�V�H�F�����AŢ希��Ý��ţ����Ť�\��Ŧ����ť�~�o�ũٯ��Ũŧ���r�z�s������ʝ�Zē�v�a�x���YŪ�����J�P���a�������e",
			"N��kū����@�w��Ŭ��e��ŭ�ՓxŮ�ϻs�S�Z���H��űŰ���f�qů���`���Q�\�GŲ�j�Г����Dŵ�����S��ﻘ`�L�Z�Kų�¼X�zŴ����",
			"O�˓���Ŷ�p��j�MکŷŹ�Ÿ�p�W��T�p�k��˚��t��Żż�U���qź��Ž�Y�a",
			"P���Zڕ�T�WΌї雜��������^���J�Ј�E�U�i�l�n�a�J�MΓ�q�z���w�ٷK�v�@�p�q�Տ̾��q�T�E�R��ˑր����Q����W�p�u�B�����t���K�_���t�F���m�o���L���[�b�^ƞ�Oćړ���v���T",
			"P�����v���f���L�i��͗��⍷��hÇ�T���~ڢҔ�ۊr�qſ�už�����������ᝏ������В��ٽ���ŪT���ƹu݇�������������W�s�e�������ݖ����A�����g�����ͿT��b�ۘ�o�Q�m���Лc����",
			"P�Ѡ��������j���G��b����қP�`�T�p����t�Q���������}���U����������I�����I�o�����ג������؈������ڞ䠏�����d�N�ь���܊E���尒�h�a�B�P�^�ސC�S�����[���k���������",
			"P�����p�r䞂_������������췛֫��丟Ʉ���]�����\�燊��\������Ȇ�����M�τ����ꛀ�y�g��Z��y�J�o�p���s�y�M�󒲸����lܡ������Ă��X�k�s�m��@�������՟ԑu���~�����A�",
			"P��e���e��i�u�J�m���A�v���������s�����C�n��ا������������W���y�̠�������u�y��������\�w�t��Y����i�y�����C�B���F�G�V��Ƥ�o�ōB�����Y����ƣ���ۯ��ơ�����n",
			"P�u������Ƣ�M�[����m���R�����Q�f�K�����`�d�Cܱ�Kƥ�������kØƦ��|�aߨ��񱇺ƨ�Ĝk�F�ǋ��D�Ƨ��궯@Ʃ���GƬ��ƫ�x��ƪ���@�����A��F�X՗�j����NҐ���G՛ƭ���]�_",
			"P�⏮Ư��Ʈ�����~�H���g�h�wư�i�Q��g�o���Ʊ�G�����ΏґG�Ʋ�ŕ�Ƴد���v���ƴ�I�|�P�D�nƶؚ�V��Ƶ�l���d������AƷ鯖W����Ƹƹ�jٷ�ڳf�E�z�Zƽ��ƾ��ƺ�Jƻ�Z",
			"P�����қ��r�L��ƿ�Ύ��KƼ�g�B���v�J��ɑ̓�u�Z�҄R�k��݃�{�G�q�q�O�����Ê���Ĝ��N���w�k�ŇM�X۶��c���ό������O�p�g�F�Ȕ��^����\�B�Ƴk���b�H�����H�g�h�������r",
			"P���V����͆R����˒p�ʎ}�~��ꆯj���m�ۓ�䁓����O������ǎ���h�уW�T���姲r�o��ٟ�b�h�������֟M�Շ��ߕ��ם�����E�k���V����m�n��",
			"Q���x���]�I��Ԉܕ�����m�E��������B�M�I�^�M�g���X�����Q�U�u�M���a���pࠔ����������s�S������d�e�e�l�g�W���ʆkÍ�r�y�����Y�~�Bȓ�u�����ߟ}���М��f�KὍ��������C����",
			"Q�}̈́�H�^�M���������[�V���������ڠ�L�R�z�l���L�ԏ��X�����j�����a���_�K�M���U�����L�F���ȠRň�B����ހ�����₈���������V�ݐ��ݒݗR�D�O���ۼ��z�[�ґi������e�h�mՃ",
			"Q����t�K����������᪌�������������X�O�g�~�Ɣ������T�[�����鎩��俜j�X��ݽږܙ�H������������Ӑ泞�J�w���n�o�컞�L��N�z�B��D�Qޭ�a�B�������s������Ě�T�U���I��",
			"Q�u�}��K�dŠϓ�G��y���O����ߌ�����ܻ���轫^���u�M����������粕������H���M���������������H�Z�����ű[����������ә������ԗ��������ӓ����P�\�r�s�����w���j�J��τ��",
			"Q�֒�ڞ�M었X�ǡǢ���s����ǧǪ��dǤ��ܷǨ��ᩖe�F�@�dǥ��ǣ�x��O�@Ǧ�����Tǫ�e�L��ǩ��e库��ù�Ս�w��v���o�p�����c�k�q�q�B�R�S�`�a������R���qǰ�Ԛk�ǮǯǬ",
			"Q����bܝ�`�j�Q���p���EǱ�N�Xǭ�Z�b�c�K�`���R��ǳ�ɜ\���ǲ�S��Ǵ���`�l�cǷ�X����ٻ��ǵ��Ƕ�����|�`�qǸ�g�����G݀�R����yǺǼ��ꨔ�ǹ�o�]�j�Ć󗾪}ǻ�ܜ���ﺍ�ꘌ",
			"Q�����z�Ϻ[�ۄ��ۖ�j�I�j��ǿǽ��Ǿ�ɝ\�@���ԙ{���mŚ�b���Ǔ��u���������H�����������������b�z�������������^���`�N�������ش���@��ډ�E�F�����J���ų~�̃S���ۇa���",
			"Q�w���Ԙ�֯��ƴ�˖�Sڈ�y�X������~��ڽ�~�͎��Ϛ����V�|�s���ʸ[�N�N���j�m���҅���ӅL�Ԃ�������㫛�����͉�A��f���D�o�l�~��@烸`�]���������V�B�]���W���H�z�",
			"Q���������ۈ��������lǛ�Z���٬l���s�d���������ܔ��V���՝௝����ψ�a�T���v�c����ތ��u�O���Ēa�߆wǙ��l��p�C�i�����������W����[��A�F���p���[��_�����隄�|�痳",
			"Q��ȍ�������������������핝NՈ��������᳠���m�c�����D�����^���ƅo���������H���^�ğw�z���|��͋���ű��`�F���w�K�W�}�\˕���H��w�n��k�x�ǋp�c��j�E���~�hڂ���b�F",
			"Q�G�U�p�q���������@��U�_�F������Aٴ�aӈӉ���p���z�iޝ��ᖗW���������U���Ϝ����H�ٟ���͏�Î��p�g�G���M�b�F�����j�@�A�z�������҅J�ڰ�r���o���E�|�����lР�^�����L��",
			"Q���x�����l�t���o�n�L�څ�D�|��������O��۾����ǆ�d�T���Pȁ�@ޡ�S���J�z��J�g��޾�Z�ߞ����묻c�J��R���d���Yȡ�lȢ���s�yȣ�x��ȥ�`��ޑ�T�^����Ȥ��C��N�U�z�Y",
			"Q�Z�Ȧ������g�zȫȨ��ڹ��Ȫ����ȭ������������Ȭ�o�������ܽh�Cț���T���jԏ�b��㌘��m�Xȩ�B���܌A�k�e���j�Sȧ�EȮ�L�繾J̆Ȱȯ�����ф��j��Ȳȱ�Uȳȴ������ȸ��ȷ",
			"Q�׉U�n����ȵ��ȶ���U���_�|�P頵C�I�o�]���n�Ҍl��ȹ�tȺ�dۧ",
			"R�ʃ����߅m�c��󐒝�c���uĞ�f�ț�ۜ��ǌ�y�A��ہ���@�݅��VЀЅ�cЙ��Ȼ�Y��ȼ����Ƚ����Ⱦ�z�v�G�y���K��ȿ��`�X���������}�Ñ��j׌��������Y��������N�_���v�@���ȟ�",
			"R�����ɏ�Z��e�\�m��������ߖ��Y�����B�r�ЄU������ך�Ό�P���іk�����M����⿊��x�ż�ӕܐ�e�G�V�H�z�~�g��J������w�i�e����R���J�~�_����������Ǝ�����ݍt���V����",
			"R�q�s���m�r���x�����Řs�۬��Z��ђ�F�տ^��΍���V�h�g�q��ϔ�ߌ]���P�\���b�Ề�Y���n�|�y�\���j��݊����~�k�k���Q���q��`�]ߏ���������M靖x�T�S�ʇ���n������޸",
			"R����^������}��p���N�����r��C�z�d䲆䋇�������M��d�J��ɉ��������X��ܛ�\�ެ}�Q���M���p݉�w�O�M�c�qި���t�G�B�G�H�����������������c�t������ټ���e�S�c�x��",
			"R���m��O�k�}���U",
			"S�G��輔c���u�������Ђ�_�����������\і�x���v��j���D�\������~���k�wݐ�W���B�Y�m���\�p�g�Γ����d�p�~�f�r���^�A�y�t��ح�H�F�i�}�X�[��Ҏ�ǝ����������ʔ�{�ѷD�L",
			"S�̜V����T�U�ɏZ�N�A�m�I�������ؒ���E�����Q생�ئ��������M�`�S�_���Ӛ����H�������T�����|�����wِ��̃���q��맚Ʌx�Р��L��ɡ��ɢ�ּB�̙V�R�V�W���D���^�d��ɣ��ɤ��",
			"S��ј��r�ɥ�ʒ���ɦ��ɧ�������b�X���fɨ��ɩܣ���ײ��ɫ��ɬ�Ĝi駱m�X��ɪ�o�C�����������𣝭�i�i�w�N���Q�m֠�o�S�{ɭ���Iɮ�O�~�Lɱɳɴ�oɰ�~���Q�}��ɯ����e��",
			"S�������|�����\ɵ��������ɶ���S���ɷ�������ɸ�Y�k��ɹ��ɽ��ߍ�Zɾ�hɼ�u���������Gɺ���ZÈܑ���^�Ǆ����A��ɿ�`�����ܙc��������������W���������@ڨ����ɻ��Ә",
			"S�]���Ɨ����R۷���������Ô����Ŵ��b�Ŀ���i٠����~�����X�����������ʑ^�C�K�����D�xօ�l����������p�l�A�ρ����Аv紾y�������՟��Ԕ��������iʖ����}���֖��x����ۿ",
			"S�p�����ڊ�Ќ�B�K���������f�d�h��������ͅ����������h��������h�O�⏍�����ܑb���Jχ�s�M�؞�������J������늍�p���Aڷ����|���r��v�`�m������Ȑ�[�Y�KɆԖ",
			"S�e�Q���M������_�Y����Y�h��ߕ�z�b��������q��ן�����B��Ք�T���ֲs��c�\�}���L�Y���ϛرs����Ì�I����驯}�םB�v�������j�����ΕN����Ɓ�}���{�iʤꅕ���Ϝ������H",
			"S����|�������Wʡ�򂯜�ʥ�ɕ���ʢʣ���K���}���|�o�Tًʬʧʦ��ʭʫߟ�\��ʩ��ʨ���J��ʪȞ�ۜ�{�N��Ԋ�����O�t�[Ѡ����X���P�|�i�ʮ�ʲʯ�y��ʱ�bʶʵ�g�E�z�]ʰ�µuʴ",
			"Sʳ���rݪ�����Y�P�֜��P�t�g�v�I���Z������ʷʸ�d��ʹʼʻ��ʺ�E�V��ʿ�������F����ʾ�bʽ�������ƅ�������΃��Ґ^�����ǖ����c���ʖ���i�x��������ҕው�߱�J���B�̈́���",
			"S�s�������lԇ�Y���k�Ċ]�ɋҝ�՜՞�}�S󧺏���n�|�����������؈�����������������緯l�f���ݾR�ު��F��������喀���x歖�ٿ�����⼂������Y��ܓ�g�E������먚̽����S�\",
			"S����ݔ�]�_�d���e��������������q�H������������������P�e�^�l�P�O�n�t�X�����������J�X�����Oˡ�����R�D�g�V���w�������������Q����f�T��̠ˢ�ˣ�X˥ˤ˦˧���i��˩",
			"S�V˨���Y˫��˪�p���Z���ܵd�{�t�C�L�Uˬ�u�S���Y�`��˭ß�l��ˮ���j����絈˰�c˯˱�J˳˴�ʊ���i�p˲�B˵��˸˷��F˶�����������ôT�l��˿˾�i˽�Лq�h˼�l�w˹�z��",
			"S�ϗ��D�j�t�@�˘{�L�Q�F��˻�z�P˺����ʑ·�J�a�z�\�l�r���D���ȁ������������Ɓ���榛�����K������ٹ�ٖƠ��t�����B�~�����L�rҖ�����|�T�ʜ���I�[�A�r���ɖ���������",
			"Sڡ�s����ݿ�Գ��@���������㤒�������ؑZ������������A��b�m捃���nಎ��C���Ѫv�L�r��쬓��������g�}�t�`�p�ł������޴�\˒�����ծd���ո@�d���K�V�Ň��׫T����",
			"S������䳫����ٚ����X�����V��༉O�܋���ݜ��C�i�h�ې�j�h�����x�p���ɝ��_�M�i�X��˂�qۑ��T��⡯i��W�{���g������V�䂋�a��ݴ�]��Ț������m�������U���S��Ď�v",
			"S�l�聂��Z��ǈ���Ü��q�r������ӝܷ[�r�w�p�ݭj�X��u���`������Z�w�\�����ݥ⸓q�s�p�ʘ�V�p�������p龹��{��������j�������������������t�w�z�s�����������",
			"S�R������Ŭ��a�i���C���R����ݷ��դ��",
			"T�O���g�{�U�s�W鋲_�K���D�k���I�I�m��ɔ��⑓�ޅއ���E�Q���_�]�^߾�fܖ�J�D�ۇd���Z�h�����Tʎ���o�W�]��c�\�D��g�w�l�X�B��⚵�ɉ�����g�c�≒�d��Ԙ���Wу�P���ڎݑ�",
			"T��܀�~��o���h�_�����ݝ���d���p�U����埊U���@�Iπ�W�՝g���V������|�Л쬟�����f�l���������k���������d��я�D�@����̡�]���H��̢�H�ˍ������e���`齚϶N����w̤���J��",
			"T��̣��F�O�Y�n�c�k��L��̨̥ۢ�ŗ̦�ƞ����عx�_�U���T��E޷���F̫�����̭̬����̩���v̪⁜̑B�M̮̰�Z���a�j؝̯̲�c��̱�Z�����c̳꼂�̸۰�򐴏����W̵��̷������̶",
			"TՄ�]����̴�t��˓���T؍�v�Z������̹̻���I̺�g���������a�f̾̿��̽�蜞�N�@̼�l�U�y��驪R�����v�T��ۏ�M�|�U��⼈n���Âچ����o���������y�G�y�f��詶K�g�̴g�C�Ř��L���",
			"T�}�Q��Z���h�n�O�y�S���Ȃ����������E憃��ܕ򠇲���̓��ˠC�����|�����ͽd�|���������Ϙ����_�l���z�N�w��ޏ�G����������������ѵ��i�T�I��[���c񊙄�Pػ����ӑ�z",
			"T��߯��ؖÎ�����cĆ���ۯ\�����ܝb��߂�gΟ��`�����v�L���T̄�I�L�z�Y���������R�e�f��簂����䍨����v����H�X�Æ٬v���Y�ӝz��ʃ�y��ڄ�����pۇ����}�{�Y�f�[�����咫",
			"T�n�e܃�w�����ꛢ������ѐ�����P���W��N���{�����󛭃�G��̊����L�j�V�p��ěp��x�b���\���J����ٴ[�g�k�K���k�D�c�l���傁�����לL���_���`�t������C�q�`�t��",
			"T���V�q��٬�������w�K�����G���x�l��ɂ�������p������C���x�f���i�q�I�x��A�I�����нrқ�q���\�g�����N���u�c����F�����������͡�J���[�����N����a �dߋͤͥ͢��",
			"Tͣ�Í��s�j���ї��w����b�F���N�K��ͦ����P�EÉͧ�F�P�b�c��h���ͨ�]��Ɍ������ͬ١ͮ�M��L�I��zͩ�Ϟ����U����ͭͯ���p�hͪ�P�ׄ��~������ӖS�H�Ԡ�Āͫ�jͳͱͰͲ",
			"T�y����ʹ�Q�q͵���BͷͶ���^���}�����W�e͸͹�dͺ�Lͻ�l�ޒ؈����fȋ����W�C��ͼ��x��ͽ��Ϳݱ;���^�\���T�į��\�]�\⊈D�E�O�T���G��B�I���h�����M�B���Q��ރ��ǁܢ��",
			"T�r�Īl��؇�Ň��҈F�_����昼a�o���C��蜨щ��ɗ˔���P�j�k�n�s�~�L۝�QÕ�ȃUۃ�h�ˊ��������D�̅זN�l���`���X��Z��ܔ������ę�ۮ��dرי�К����ϛk�M�hЛӚ��Ó",
			"T������٢����A�������ȳaІ�ҽF�������P�W���j�|�����D�r���n������ך��Ջs�֗����E�K������ڗ����X",
			"U�m�i�C�_���H�H�x�IɅ�S�E������Ɗ���ĖG���C����ņ�T�[�e�˚`���wˀ�J�����q�s",
			"W�~���f������X�v�K�P؞�����i�z�J����Ċ�n֜�x���M���^���N�oş�H���[�h�j�h�����ψ����i��W�^���s�O���z셲y�w���ÌR�筎�r�T�ڬ]����z�܄����ޏ�����洮|���z���ܓ�",
			"W�Νj�D�|������ߜ�����[�������e�m�c��ᆷ���������������G�屛�ꝟ�㏝�Ϟ���\����ܹ����e�渊�w������Tؙ�Bߐ���{�n�����D������i����ҕ����������O�U�n�l�j�",
			"W��d�e�v�����f�D�[�H�sٖ�~�@���������������������w�^��������������D������͇���W�\�s�y�_������ނ���Z���RΣ�����������ҋW�n�̓G�f�w��Ȗ��΢�ܘL����ԕ�J�k�A�Uޱ��",
			"W�IΡ�g�h��ΪΤΧ����Υ�Ǎ_�e�����fΦ�Ψ�Ωά���͎��������`Ϋɖ�������H���d�L�W���Sΰαβγ�Yέί����渒˛��U�Â��΍��|�uή���[���@�ΐ��Ȕ�J�\�]���۟��|��",
			"W�^��踃^�S�O�V�ی����lՆ�c�l�Q���S��n���t�]�w������δλζƄηθ�ξ�}ǋνι�yμ⬟���εο�ݠҴo���o�l����M�W�o�^�]�K�G�E�jκ˗�E�A�G���v���~�Z׈�^�j�e���ج��v",
			"W���n����w�����ď����[�ɳR�ży�P�ë���������b���Y�jΝ���R����Z���ǅ؏�^���W�ɗSÂ�ȷg���ʊp���|���bÁ�h�����������lΊ�f�O�T�R�ǜ���\��޳�Y���N������",
			"Wݫ����u�i�n��ѸC��΁�b�Ҋ���Ӂ�������P���_����ןs�җ��O�ӲY���x���}���؏v�����ؖg���ݛ�����ڏ�����E�w���G�_��Ώ���u������������������ƕ�`�@���c�o����ʏ�N�M",
			"W�����~�W�������������������ÕJ��b��W���憕�������]���ʴI�Ĭ��苳�T�����q����W�^�Rأ�������ば��N����̏���J����Ր��򐚻|�A�����č����F�}���V�Μ���廟��`����",
			"W�P��H�F�I�F�\�F",
			"X�G�߉��Q�L�׼Y�W����b�e�Kՙ���o�mҎ�Q�q�`�k��֛ކƒ��B�AΘ�۟��_�E�{���\�֗�G�S�i��ۨ�B��߀�I�G���f�@ȝ�F������D�]�M�A���т��W�����x�g�a��Ҋ�f�v���B�g�^��",
			"X�R�n���T�l�y�g�y�r���A�m�����Q�����劮�F���ɉA�R�v�E�L���y�����Z፜��_�`�S�x�i�m�[��ق���D�b�c��֎�I�P���j���[����O�aݡ�{�n�����]���f�򱍽���ɒ�G�@�a�s�b�F�����Z",
			"X�v�]����l�w�}끚��f��៿��z�Y����j�g�G�a�C�_�����E�����޴c�����O�Z�A���h�u�ϚY�]Ɋ�U�����|��Ϧ���ϫ��҂��ϣ�k��������Z�[�`���Yۭ����Ɋ֌�Ϣ����������L�b��Ϥ",
			"Xϧ�N����lϩ�X�_�N��ݾ�T�R�ݐ������mϬ��ϡ���������q�w�Ə�Ϫ����Y���Ҙ~Ϩ���k���g�F���q���ү�ϥ�O�D�ؘ������O���Ή�a�O�ײq����G�H�l؉�G�v���T�}�@���^�^�d��",
			"X�x���@ӂ���P��ӄ�ϰ�Eϯ��Ϯ��ϱ���j�t��dҠ�v��΀�@��ϭ֐��I���e�w��u�@�N��ϴ����ϳϲ�}���|��S��㊑����ʙS���L���u�l�kے�t�^���{�h�[�cϷ��ϵ⾅��Oϸ�S�_�_�S",
			"X�@�����M�҉I�����i϶���������V�K���̷G���ʓ�B���_��̟�q�]���S�U�aϺ�B���i�����PϹ�r�yϻ����bϿ�Ԟ���ꃍ{���M�����̸����{ꘜ����ړ�Ͼ覹d�W�rϽ�Eʛ�[�Y��ݠ�",
			"Xϼ�_���h�T�p�B���ňY�K�ė��B�LՒ���������]�Ɂ����Ȋh�ˁُ�믖}��i�x�̫�ݲ��������ǃM�m�J�����턑��v���D��r�M۟���`�s�]�o�N�v�]�w�������Њ������̒����j浊ދM�L",
			"X���p�Ά���̀�e���ӮQ㕋������͝��t�t�Pݍ�_�B�Gˁ�y�U�D�v���������A���՚��������`�ڌ����{��͘�й�����޺���Ҕg���\�N�`�@������������`�ފ��ܱhꈆZ�����ҍs����",
			"X�{�ݬF�ڱ��m�����׻��كg�n�Q�^�Ⱦ��}����h�D�W�R�n�@�I�`�o���E��ܼ�����_�ᆓ�l�m��������x�U�G��|č��������֭����`��J�������Ԗ���|���K�큉���J�Õ}�����}�A�߇�",
			"Xφ�������z����������e���������V��}�P�������ɂP��������n����|���̍���^����n�^�V���e�h���������p���`���E�R�����X�^���ߪ��N����ąʒ���{�n�}�r�S�]�y��",
			"X���t���v�n�Ƈ̙��u�j�D򔚮̇���U�����F�qС������q�~�ԺS�j��ТФ���D�k�j��ЧУ��ЦХ�唬��ԉ�C�V�[�[�^���ЩШЪЫϐ��Э��а�fв���G�e�����{�|�~�~��бг�n�e����",
			"X��Я�������n��ߢ�������qЬ�C�X�X�P�p�y�iא��д�挑ˆ�Ğ�йк�m���ж����l��м�Ȃď�е�c�c��r�ѓa�ͽuл�D�f���р���ԕ�텎O��и��⳼I�Zޯ�����C�x��a嬠yзϒ�^�k",
			"X�K�a������ߔ�|��о��꿖����^�r���dп��짏Q������нܰ���Q���g熁����c��ض�J����܌Ò���M�{�^�g���ˠ��ǈ��U���ɟ��w��͍�q�����d�_���w�H������������D�Q�͛��ꀊ���",
			"X�R��]�o��t�M��ߩ�����������n��㬛�D�m���B���փ���ܺכ���چM�r���r���KԞ����ל�w�锸�݂c�����Ӟ����Ã��^���ʘ������T�x���}��q�v�ཏ���N��ᶫ�����L���彑",
			"X�P�fΐ���C�V�n�M��B�L�������Ӓ��̐��נ�����T�H̓횗췠���P���q�u����[�Hʌ�d�_՚֞�z��`�P�T���[��銐ڼ���򫍂��S����Ԃ�������S�����T��r�U�����d䪈��A�~��",
			"X���ÄԔ����T������ĉ����������[��A��A���[���s���N�W�wㄷV�{�x˅�mޣ�����R�I���t܎�]�����H�l�ː������k���Ӭu�{�U�ضP���A�h��ʞ՝�X�M�C���Q�B�Tύ�X�~�z���t����",
			"X���I͕������诙e�v��ѡ�@���xѢ�_�R���]��ѤѣО��K���f�ֽk��C���R����X������K�T�HѥѦ�YѨ�āl�yѧ���NƋ��Џ�`���W�G�͠K�{�z��ѩ���}Ŗ�G���LѪ�ɐV�����N�������p",
			"X�o�y�_ѫ���[�׉_Ѭ��M����`񏇠���޹�֠`ĝ���Q�ˠo�c��ѰѲѮѱ�hѯ����䱼r���՗D�����Ō�ѭ�Mԃ�Z�����x���ߟ��@�R�W�y�S�\���_ѵѶ��ѴѸ���ߪFޙѷѳӍӖӜ�Q��",
			"X���d���b�eަ��R��",
			"Y��ٌ�K�r�a�C�jȀ�u��g��������������Y���]����Ӕ�P���F�i�ߊ��ד~鑟��m�Nݑ�����M���r���X���������k���N틟�Є��ы�Y�����~�l�]�b���Ğ�e���o�\�s�A�R�h؋x�]�~���",
			"Y�@󖘷؂���V�`�^���Q���D�r��T�����Ǉy����萛S�{���p��W�v�_�U�d�m���_�J����΃�l�Lʁ�ƍ��J䄒��S���F�I�K�j�P�A�����uጆd�f�h�q�p�V�S�B�}�@�U������ڌ�f㎗H�c�U����",
			"Y��͑��L���ɠ�ސ�q�����^�������V�^�S�y�_�e���X�dݘݜ��ğ�@�A���T���N�ӟy���O�]���P�l���W�m���j���U�M�w�w�GԔ�l�H�����X���DÑфх�P���v�r�C�j�e��Ѿѹѽ��Ѻѻ��Ѽ�S",
			"Y蛗��f�E�����s������ѿ���������ۍ����Īc�����ÝQ�\�|���Ɔs�����ů{ʋ���L�Ƿ�ҁ�ȁ������e��櫒����o번��I��Ӡ���m�y�B���E���������̫��ق���������������B��",
			"Y۳�̝v�����Z鎋�c���E�iڥ����������ܾ��Ӆ�ҕV�����I���ԪP���r���άJ�x�P�Z�֍����۽��щc�C��ԝ�N�B��̚鐅��������i�������̌E�r�s�v��h�}�����Z�W�m����ٲ���]�m",
			"Y�����������T۱�D���ݑ��R���{�����V�C�㳚��s��ъ���i�ʇ{�y�t���d���[�o���f�d�����j�k��B�t���|���z�s��z�_������������������ҍ�邩��g��Ꚇ��ߔ����ͪ_��Ȋ������H",
			"Y�����������������e�����V���e�������V�Y�����Fٞ܂�w�z�z�`���IӃׅ������G����F��ח�V�W����󊚒t�����o�j�����Z����}�������g���D�������������r�����[���f�ȫ���",
			"Yꖍ����P�Ք�����@���U݌����^�{�u�R�F�n���ֈt�I�����������D��D�I�����y�B�Ĕa�Y�W�S�������k�h��������زߺ�����n�@���@�����^��سҢ���Ȉ�Ҧ�i���x����Ҥ���e",
			"Y��ҥ�U�ڋ����uҡ�r�bң�����l������uA�C�ٴt�G�H�P���{�|���_�i������a��q�Ú|���wҧ�̱l���Ҩ���Q�ʜȘe����[�o�r��ҩҪЉ���Oș�o��ҝ쉪����a�G�נdŗˎ����ҫ",
			"Y�f�_׊耂�Ҭ��ҭ���JүҮ���������X�y�U�IҲ����ұ��Ұ�S�c��ҵҶҷҳ��ҹ�w��������ʖ���ҴҺ�ˈ욇Ҹ�~�v���G�I�@�p�ϕКS��@���w���̎I�J���]�Y���L��|�E�K�{�B�d�v�w",
			"Y�v�B�EČ��һ�o�v������ҽ�������t�ޛ�⢮��cҿҼҾ��~�B�����F㞋���߭C�p�������t���b�s�p�U�ǅF���ăތb��ڱ�������n���t�������̍[���q���A����ޖ�h�����ؖ�����Б����",
			"Y���U�f���r͆�r�O�ŕ����B�D�����Ƀx���z�K����Β�U�V���ںm�{�k�͏�֖�F�J�~ׂ�@�������~�Ɓ̔������q��������Б���ޠ�Í������r�i�C��}��Ε�}�EŜρ��T�t�V����߮����",
			"Y������E���d����������N������߽�`���֕��p�k�i����٫���Ố@���ז��u��������X�Ȏ����Ė����p�����z��j����ږ���ś���К��ؗꋄֈ������������p����^�T�U�[�\���N�c",
			"Y�������������|͂�mژ�z�`�{������~�m�v���x�����`Ԅ�㋝�M�]�����jɚ����k�|�̘����ڟ֟鯎�x���k�o�]��I���΋ڎF��������۠D���e�j�OŒ޲Μ�A�����ٙj�c�J�W������؊�l",
			"Y�˄ˇٓ����X�[�s�J�G���������[�g�h�y�{�����O�^�gܲ�~�A��̈��ה�~�]�����f����������������S�����ꎃ����P�����ܧ�A�ֹN�s�P�޵��M�a�@��㟴�����N�񗇑�@���",
			"Y�������g�󛎇�|���������Zӗ���H���������C���w�����H۴��_ӝ�z�y���w���K�����r���]�l��������������^�Y�i��L�@�y�a��[�\���P�a�Ӱa׍ӡ�ᛝط�����Z�J�S�\�E����",
			"Y�g����Ӧ��Ӣ���@��ݺ��Ӥ�k�ᜀ���P�������Q�A������O�xӧ��s�aӣ謇|��њ����Ћ둪��팮Z�vӥ�D���[�t�����c�ѭ��_�s�L�]�t�N��܅���K�W��ӭ��ӯ��ӫӨөӪ���w�Ӝ���",
			"Y�t�O�L��������Ӭ��΄���I�MΞ�����L�p�L�hӮ�c�l�w孞u��ω�՞��Y���A�k�A۫�w��Iӱ��Ӱ�}�f�e�g�_獰`ӳ��Ӳ���{왞]�GӴࡆ�ӶӵӸ��ӹ����{Ӻܭ��㼝K�������Փ��",
			"Y�a��ӷ�M�t�O���b�����I�x�b��J������ӽ�[Ӿٸ�ʖԈ��~��ӿ���搾�Ӝ���ԁ�M�ӏ���Ӽ��ӻ�H���x�����o�l�k�������ϐQ�|�����~�H�n�������ɞX���l�i�����ɛY���ʐJ���U�M",
			"Y�������r���xݯ�xݵޜ�]�˂����I�K�Ϊq�[�ϘA�����j�f�~����ݒ�O��߈���ЁJ���h���h���gݬ�X�uÅ���K��͜�B��뻠�����������٧�M���n�N��嶍f���v��ޔ�e�`�ʁ��z���R�T��",
			"Y�G�����}���|�u�G���ٱE�����z�ځ���ߎ����楒T�f죫]�_������s�����خ����Ɯ�S�ʊ��������C�������D������~����������K�������������^�ޘ@�ܚQ���ń����O�I���ш",
			"Y�u�|�v��՘�k�N�䑵���D�u�e�m�Lݛ哵H�~�k�����B�e�V�i�}�C������������Rٶ��������}�����P���ה��h�g�h螂����ї�r���c�Z���o�����Z؅�����P�r��Ԧ�����R����������",
			"Y�����rƑ�q����ԡ�_��Ԥ����֐������M�U�@���N�І������VԢ�����������~�Z��ԣ����S�������ϷC�N�f������A�����ع�z���s�C���h��T�o�j�Uəʠԥ�y�`�\�˟���ʚ�I�[�����",
			"Y�������R�r�ְK�N�{�h���q�O���|�]�u�X��M������Z�N���O��܆�c�d���n�����uԩ������ԧ�w�a�eԨ�m���Y�A���d͛�g���S�Q�r��x���t������Ԫ�OߖԱ԰��zԫ�ؒԭ�TԲ���WԬ",
			"Y���J��Ԯ���jԵ����@�Aܫ����Դ��Գ�x�V�g�rԯ���F�m�z�����~�Q��w�@�x慙�߇�{���M��Զ�\�R�h���O��ԷԹԺ���c������襵�Ը�jцѓ���Ի��Լ�s���E������뾌�`�j�����h",
			"Y�xԿ�����Q�R܋���Ē�ڔԾ��Խ�_���X�釋��кM�[�߻C��g�g�~�^�S�a�N�l���V�C���Q���[΂�S�f�Nٚ�Ƅ��ȁ��ԇ�u�l�V�ܿ���s�d�n�Ǜ鼋���m�y념�뵜ݹo�l����|�]���J�a",
			"Y�I���m�@�d��j���\�憽�q�E��ю�a�p�B�q�y���˖�۩����i�͂֐�����\�C���Z����ٿAʕ�̿Zʟل�d�j�a˜�r�y�N��@",
			"Z���Ç�֍�M�i�Ԃȅ�����K���cۂƏ�]�قt�����L�M���{֚�nލ��\�f�b�w�~���q�����l�g�tތ�םr�~���u�x���o��ԗ�g���c�I�x���E�������m�������J�}�w�d�F��ڝ��ɐ���о��|����",
			"Z���m�緉�e�阧���y���\�x�����Z���P��ې�n�G�����a�����h�����[�y�c�J�{�y��\�l���������Z�q��R��ƖØ��i���M�ߜ��G���C���������u�a�}��D�Z�e���I�R���Z�U�T�t�F�^ɛ",
			"Z�e�I�BÏ�h���I���l�B�`�G�K�m�����W���o�V�R���`�t�P�Rǟ���X���ǌ��w�I�l�����d�����z�J���R�~��m�K�y�\˟����̌�B���~Ǡ�f�Ëq�S��瑉\�G�z�@��n���r�������t���`�i��",
			"Z�H����l�X�C�u�[�E�v��W�M��V�y�A���a�C�\�S�]�c�|�ł����s���h���W�Z�՛��V♈S�����ћe���ٛj�������N���H�N������j���S�s���{���������Ԟ�Ǐ���ֲPن�������d���ڒD",
			"Z�����P���f����������ۂ̆��Ìv�˃��܃�����ڎ�ݕ��m�������Uۊ����ٝ�Y��A�Ԟ��{���`�{ד𕠙�n���_��j�E�v�N���z�ʏn���Z���QĠ�K������ۛ�s���������闗�F��b�k����",
			"Z�o��r����_�בV��Ł��r��Y�Yڋ��^������k�g��z�����ńt����������؟�ڲ��K�������j���tՋ�ӓ�ɰ��c�j�d���]։ّϏ�`�v�B�Ɖ��ٚ�꾕W���������\��ό�f�e�����P�U�ו�",
			"Z���������ՙI��Q�D������֟�_��{���ٛ߸ށզ�s�������������髄��������u���A���O�����h��܈բ�lա�����l���Q�~�zգ�ē������z�W�~է��թ��L��ը�o�����p����ե�m��",
			"Z�y��ի��ժ�z�Sլ��խ�Sծ�΂�կ�մձ칖�ճ�t��a�jղ�n�އ~�E�U߁땚֚�հ�r���d���@��}�g�Dז��ն�sչյո�جW���K���^շ�Q���\��ۅݚ���rռ��սջ�Cվ����Ǖ��տ��",
			"Z�`���u��̛̜�O�Jպ��ŏ{���@۵���ÑP��⯻�ɟ�l������\��b��J�����Ǜ��Ɲq�Ǵ������̒E�������˻w��Û�o�ωz����~���βd���Ȋ������ўݱ@����D�q�S噠����Ӭ�����",
			"Zگ�����D�����A�������t���ֹ|���D�w�נY�e�����^�؋��څz�ۚy�K�Y�q�E�܈���П����������������Ԁ��ߡ�m�����t�O���U��ֆ֕���H�y�xׄ���������N������J��������V�p������",
			"Z��䫂ؑ������w������ᘂɔ��F�I�z��Ȝ�Z����E���絝�R����k默������U�G���������m�gݟ��P�E�y����r�튪���_�������pА�G�r�Z�]Ҙ�\�F������}�b�j�t�I�m�������E",
			"Z�������ޖڼ�����`�L��͖���g�c������c���l�J���͊���������������A�k�ۈ�������b���to�@�ݏѱ����`�չ~���t�P�P�@�Y��c�l�����^���������֤ں֣֡��֢���^��Պ",
			"Z�g�C֧֮ش֥֭֨���D֦֪֯֫������o�q�e���}Ё�u�U���~֬�b�d����]�w�A���u֩�\�T�}�����u�~�_ִֶ�pֱ����ֵ�p�����ְֲֳ�����ŭ��~���c���e�Z�ܘ�Ĉ�{�dەۗ܁�Uֹ",
			"Zֻ�M��ּ�nַ�^���W�E�bֽ�ƒn��Ƈ��dָ�כ��U��]�W���Jֺ�T����ˌ�j�e���W־��X���ƅ�������������ۤ�f�Ŏ�蒔���Λ��w��͏�ֿ�y��O����З�����v���������a���̶�����",
			"Z����И�dؠ��Ѝ�������������D�������ɹe���N�e�@��y�������F���uҞ�I㇎Ñp�����ܷW�����|���k��\�@�\��v�������S�`���a�H��ٗ���}�z�s�S�Y�T���W�v�e�U�Ё��O�d",
			"Z�q��މ�қw���Ֆ����x�����ԽK�{���W��Π����R��۠犻b����ڣ�����V�p���[�~�N�����ڊt��rƠ�{���\�������g�W�\�A�����߁��ܛ��ޞ�מ�����b�X�B�Ŝ@�B�L�b�����Q�c�{�k�q�",
			"Z񙇜�T�a�@�L�p����S������J�F���H���B���ƅ�����秃نB��q��ݧ��������Ȓ�k�L�u���t���U�����Q�N�E�세٪��ۥ������������p�N���Z���D�H�����w����T�i��f���zˠ�����}",
			"Z�^�E��{�Þ۸��A��������ɍ�Tώ���E���F�������W����e���d�C�侟����}���������هڲ�����ס�����^�ш|��ע�r��ގפ�����ǚ���ף��v���m�d�����A�q���������]�Aڟ",
			"Z�O���h�L��[���������v�B�Z����Tץ�tĐ���צק�Jר����ש�������A�U�x�u�K�m��Hת�Nܞ�|�D�������E���Q׬׫׭�Ϳx�Nٍ�N���ʻMױׯ�y���P��׮�f����װ�b���P��׳��״",
			"Z��՗[�`��ײ����׷��׵׶�F�K�x�d׹����׺㷮I�Į����i���Y׸�PՅ�^�Y�U��٘�V�V�dބ���׻Ձ�q׼�̓��ʾM�R��׿׾��پ׽�����×��k�B���q���X�V�����ƅ��������ǁQ䷟O����",
			"Z�Ć��ߗz�Ŕٕ������ڳ����M���������}Վ�r��Wߪ����媙�֑���@�h��ϗ�C�S�|�m�ЌI��Ɔ���Ɋ���Ɲ��R�|���ʍ��Ͷ�����a�����ї���Ȍ��t�U�����D�Y���ŷT�l��C���w��",
			"Z���Jڃݖ�O�����S�t�o�p�����U���b���Ӆ���梖j�I�f�c�������I�����B���������`�h�U�����TƓ���������ձ{���h�u�n���ڂ����W������تf��Ƞ�Q�ލٷO�C�h�پ��Cŋ�x�r�ٴ��q",
			"Zۙ�R���i�A�i�R�`�Q���̒֐��ߓK�i���~���t�G�Q���C�E�ݕf�S�~�`���ռF���k�S�v�������Y���ǈ�����u�t�jՌ۸���O�P�|���[�������ዃ�����{�a�a����X��������B���nۀ�����",
			"Z�����ޠ��~��M�{��֊�����j�g��F�����j�g�y��S߬���K�����`�x���쇒���r����S�E������f�Uީ��T�d��@�i�s���������׿���������V���V�gߤ�J�����}�g�ۗ��y���i�",
			"Z�����������������F����������Њ���zɁd��" };

	public static String[] getStrChineseCharList() {
		return strChineseCharList;
	}

	/**
	 * ��õ����ֵ�����ĸ
	 * */
	public static String getWordTitle(String word) {
		word = word.trim();
		if (word == null) {
			return "?";
		} else {
			String[] wordLines = getStrChineseCharList();

			int count = 0;

			for (String wordLine : wordLines) {
				count++;
				if (wordLine.contains(word)) {
					if (count == 1) {
						return word.toUpperCase();
					} else {
						return Character.toString(wordLine.charAt(0));
					}
				}
			}
			return "?";
		}
	}

	/**
	 * ��ȡ�ַ�������ĸ��
	 * */
	public static LinkedList<String> getWordTitles(String words, int length) {
		LinkedList<String> list = new LinkedList<String>();
		if (length > words.length()) {
			length = words.length();
		}

		if (length <= 0 || words.length() <= 0) {
			return new LinkedList<String>();
		}
		words = words.trim();
		for (int i = 0; i < length; i++) {
			String word = Character.toString(words.charAt(i));

			String titleStr = getWordTitle(word);
			if (titleStr != "?") {
				list.add(titleStr);
			}
		}
		return list;
	}

	public static Map<String, LinkedList<Integer>> createLetterTable(
			LinkedList<MusicInformation> musicList) {
		Map<String, LinkedList<Integer>> map = new HashMap<String, LinkedList<Integer>>();

		for (int i = 0; i < 26; i++) {
			int number = i + 65;
			char ch = (char) number;
			map.put(Character.toString(ch), new LinkedList<Integer>());
		}

		for (MusicInformation musicInformation : musicList) {
			String word = musicInformation.getFirstTitleAlphs();
			if(word==null){
				break ;
			}
			LinkedList<Integer> list = map.get(word);
			list.add(musicInformation.getLocalId());
		}
		return map;
	}
}

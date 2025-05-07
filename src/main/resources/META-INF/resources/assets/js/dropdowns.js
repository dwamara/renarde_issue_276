(function ($) {
    $(document).ready(function () {
        get_sections();

        $('#sections').change(function () {
            get_members();
        });


        function get_sections() {
            $('#sections option').not(':first').remove();

            let request = $.ajax({
                url: "http://frontend.cxs.kc.dwitech-vps.de/dropdowns/sections",
                method: "GET",
                dataType: "json"
            });

            request.done(function (sections) {
                let opt = '';
                let entities = sections.entity;
                for (let i = 0, max = entities.length; i < max; i++) {
                    opt += '<option value="' + entities[i].code + '">' + entities[i].description + '</option>';
                }
                $('#sections').append(opt);
            });

            request.fail(function (jqXHR, textStatus) {
                alert('Request failed: ' + textStatus);
            });
        }

        function get_members() {
            $('#assignees option').not(':first').remove();

            let section = $('#sections').val();
            if (section === '') {
                return false;
            }

            let request = $.ajax({
                url: "http://frontend.cxs.kc.dwitech-vps.de/dropdowns/section_members/" + section,
                method: "GET",
                dataType: "json"
            });

            request.done(function (assignees) {
                let opt = '';
                let entities = assignees.entity;
                for (let i = 0, max = entities.length; i < max; i++) {
                    opt += '<option value="' + entities[i].emailAddress + '">' + entities[i].name + '</option>';
                }
                $('#assignees').append(opt);
            });

            request.fail(function (jqXHR, textStatus) {
                alert('Request failed: ' + textStatus);
            });
        }
    });
})(jQuery);